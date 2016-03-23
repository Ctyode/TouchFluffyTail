package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.graphics.Sprite;
import org.jbox2d.dynamics.Body;

public class Floor extends Entity {

    private Sprite sprite;
    private float width;
    private float height;
    private Body floor;

    public Floor(float x, float y, float width, float height, Sprite sprite) {
        super(x, y);
        this.sprite = sprite;
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void draw() {
        sprite.draw(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void tick(float delta) {

    }

}
