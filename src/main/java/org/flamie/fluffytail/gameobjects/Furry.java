package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.graphics.Sprite;

public class Furry extends Entity {

    private Sprite sprite;
    private float width;
    private float height;

    public Furry(float x, float y, float width, float height) {
        super(x, y);
        this.width = width;
        this.height = height;

        sprite = new Sprite(Images.MORDA.getTexture());
    }

    @Override
    public void draw() {
        sprite.draw(0.5f, 0.5f, 0.2f, 0.1f);
    }

    @Override
    public void tick(float delta) {

    }

}
