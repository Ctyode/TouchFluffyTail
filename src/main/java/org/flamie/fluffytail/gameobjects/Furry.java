package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.graphics.Sprite;
import org.flamie.fluffytail.shared.Tickable;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class Furry implements Drawable, Tickable {

    private Sprite sprite;
    private Body body;

    public Furry(Body body) {
        this.body = body;

        sprite = new Sprite(Images.MORDA.getTexture());
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void draw() {
        Vec2 position = body.getPosition();
        sprite.draw(position.x, position.y, 0.4f, 0.2f);
    }

    @Override
    public void tick(float delta) {

    }

}
