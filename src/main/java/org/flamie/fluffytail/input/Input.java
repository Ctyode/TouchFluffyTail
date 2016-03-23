package org.flamie.fluffytail.input;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    private static float mouseX;
    private static float mouseY;
    private static MoveAxis playerMoveAxis = new MoveAxis();
    private static boolean jumping = false;

    public static void onKeyDown(int key, int scancode, int mods) {
        switch (key) {
            case GLFW_KEY_D:
                playerMoveAxis.startMovingPositive();
                break;
            case GLFW_KEY_A:
                playerMoveAxis.startMovingNegative();
                break;
            case GLFW_KEY_SPACE:
                jumping = true;
                break;
        }
    }

    public static void onKeyUp(int key, int scancode, int mods) {
        switch (key) {
            case GLFW_KEY_D:
                playerMoveAxis.stopMovingPositive();
                break;
            case GLFW_KEY_A:
                playerMoveAxis.stopMovingNegative();
                break;
            case GLFW_KEY_SPACE:
                jumping = false;
                break;
        }
    }

    public static void onMouseMove(float x, float y) {
        mouseX = x;
        mouseY = y;
    }

    public static void onMouseButtonDown(int button, int mods) {

    }

    public static void onMouseButtonUp(int button, int mods) {

    }

    public static void onScroll(double scrollX, double scrollY) {

    }

    public static float getMouseX() {
        return mouseX;
    }

    public static float getMouseY() {
        return mouseY;
    }

    public static MoveAxis getPlayerMoveAxis() {
        return playerMoveAxis;
    }

    public static boolean isJumping() {
        return jumping;
    }

}
