package org.flamie.fluffytail.input;

public class Input {

    private static float mouseX;
    private static float mouseY;

    public static void onKeyDown(int key, int scancode, int mods) {

    }

    public static void onKeyUp(int key, int scancode, int mods) {

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

}
