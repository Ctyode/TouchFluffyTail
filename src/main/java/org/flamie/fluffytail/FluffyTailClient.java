package org.flamie.fluffytail;

import org.flamie.fluffytail.gameobjects.GameWorld;
import org.flamie.fluffytail.input.Input;
import org.flamie.fluffytail.ui.Cursor;
import org.lwjgl.glfw.*;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALContext;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.openal.AL10.AL_POSITION;
import static org.lwjgl.openal.AL10.AL_VELOCITY;
import static org.lwjgl.openal.AL10.alListener3f;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.system.MemoryUtil.NULL;

public class FluffyTailClient {

    private static long window;
    private static int windowWidth = 800;
    private static int windowHeight = 600;
    private static float aspect;
    private static Cursor cursor;
    private static ALContext context;
    private static GameWorld gameWorld;
    private static GLFWKeyCallback glfwKeyCallback;
    private static GLFWCursorPosCallback glfwCursorPosCallback;
    private static GLFWMouseButtonCallback glfwMouseButtonCallback;
    private static GLFWScrollCallback glfwScrollCallback;
    private static GLFWWindowSizeCallback glfwWindowSizeCallback;

    public static void init() {
        if(glfwInit() != GL_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        aspect = (float)windowWidth / (float)windowHeight;
        window = glfwCreateWindow(windowWidth, windowHeight, "Touch Fluffy Tail", NULL, NULL);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(0);
        glfwShowWindow(window);
        GL.createCapabilities(false);
        context = ALContext.create();
        if(!context.getCapabilities().OpenAL10) {
            throw new IllegalStateException("Failed to create OpenAL context");
        }
        context.makeCurrent();
        alListener3f(AL_POSITION, 0.0f, 0.0f, 0.0f);
        alListener3f(AL_VELOCITY, 0.0f, 0.0f, 0.0f);
        gameWorld = GameWorld.getInstance();
        cursor = new Cursor();

        glfwKeyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if(action == GLFW_PRESS) {
                    Input.onKeyDown(key, scancode, mods);
                } else if(action == GLFW_RELEASE) {
                    Input.onKeyUp(key, scancode, mods);
                }
            }
        };
        glfwSetKeyCallback(window, glfwKeyCallback);
        glfwCursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double x, double y) {
                float mouseRelativePosX = (float)(x / windowWidth * aspect - (aspect - 1) / 2);
                float mouseRelativePosY = (float)((windowHeight - y) / windowHeight);
                cursor.onMouseMove(mouseRelativePosX, mouseRelativePosY);
                Input.onMouseMove(mouseRelativePosX, mouseRelativePosY);
            }
        };
        glfwSetCursorPosCallback(window, glfwCursorPosCallback);
        glfwMouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if(action == GLFW_PRESS) {
                    Input.onMouseButtonDown(button, mods);
                } else if(action == GLFW_RELEASE) {
                    Input.onMouseButtonUp(button, mods);
                }
            }
        };
        glfwSetMouseButtonCallback(window, glfwMouseButtonCallback);
        glfwScrollCallback = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double x, double y) {
                Input.onScroll(x, y);
            }
        };
        glfwSetScrollCallback(window, glfwScrollCallback);
        glfwWindowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                windowWidth = width;
                windowHeight = height;
                aspect = (float)windowWidth / (float)windowHeight;
            }
        };
        glfwSetWindowSizeCallback(window, glfwWindowSizeCallback);
    }

    public static void run() {
        glEnable(GL_BLEND);
        glEnable(GL_POLYGON_SMOOTH);
        glEnable(GL_LINE_SMOOTH);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0.0f, 0.98f, 0.60f, 0.0f);
        float now = (float)glfwGetTime();
        float lastUpdateTime = now;
        float delta;
        while(glfwWindowShouldClose(window) == GL_FALSE) {
            now = (float)glfwGetTime();
            delta = now - lastUpdateTime;
            tick(delta);
            draw();
            lastUpdateTime = now;
        }
        glDisable(GL_LINE_SMOOTH);
        glDisable(GL_POLYGON_SMOOTH);
        glDisable(GL_BLEND);
        glfwDestroyWindow(window);
        glfwTerminate();
        context.destroy();
        ALC.destroy();
    }

    public static void tick(float delta) {
        glfwPollEvents();
        gameWorld.tick(delta);
    }

    public static void draw() {
        glLoadIdentity();
        glViewport(0, 0, windowWidth, windowHeight);
        double offset = (aspect - 1.0) / 2.0;
        glOrtho(-offset, 1.0 + offset, 0.0, 1.0, -1.0, 1.0);
        glClear(GL_COLOR_BUFFER_BIT);
        cursor.draw();
        gameWorld.draw();
        glfwSwapBuffers(window);
    }

}
