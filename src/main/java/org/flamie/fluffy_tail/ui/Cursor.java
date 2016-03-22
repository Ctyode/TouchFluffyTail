package org.flamie.fluffy_tail.ui;

import org.flamie.fluffy_tail.enums.Images;
import org.flamie.fluffy_tail.graphics.Drawable;
import org.flamie.fluffy_tail.graphics.Sprite;
import org.flamie.fluffy_tail.input.MouseInputListener;
import org.flamie.fluffy_tail.shared.Tickable;

public class Cursor implements Tickable, Drawable, MouseInputListener {

    private Sprite cursor;
    private float x;
    private float y;

    public Cursor() {
        cursor = new Sprite(Images.CURSOR.getTexture());
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
        cursor.draw(x, y, 0.1f, 0.1f);
    }

    @Override
    public void tick(float delta) {

    }

}
