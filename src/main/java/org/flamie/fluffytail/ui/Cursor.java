package org.flamie.fluffytail.ui;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.graphics.Sprite;
import org.flamie.fluffytail.input.MouseInputListener;
import org.flamie.fluffytail.shared.Tickable;

public class Cursor implements Tickable, Drawable, MouseInputListener {

    private Sprite cursor;
    private float x;
    private float y;

    public Cursor() {
        cursor = new Sprite(Images.MORDA.getTexture());
    }

    @Override
    public void onMouseMove(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void onMouseButtonDown(float x, float y, int button, int mods) {

    }

    @Override
    public void onMouseButtonUp(float x, float y, int button, int mods) {

    }

    @Override
    public void onScroll(float x, float y, double scrollX, double scrollY) {

    }

    @Override
    public void draw() {
        cursor.draw(x - 0.05f, y - 0.05f, 0.4f, 0.2f);
    }

    @Override
    public void tick(float delta) {

    }

}
