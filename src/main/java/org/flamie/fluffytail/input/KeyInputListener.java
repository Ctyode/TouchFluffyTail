package org.flamie.fluffytail.input;

public interface KeyInputListener {

    void onKeyDown(int key, int scancode, int mods);
    void onKeyUp(int key, int scancode, int mods);

}
