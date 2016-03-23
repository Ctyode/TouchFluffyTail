package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.graphics.Sprite;
import org.flamie.fluffytail.shared.Tickable;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class Floor implements Drawable, Tickable {

    private Sprite sprite;
    private Body body;

    public Floor(Body body) {
        this.body = body;

        sprite = new Sprite(Images.MISSING_TEXTURE.getTexture());
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void draw() {
        Vec2 position = body.getPosition();
        sprite.draw(position.x, position.y, 0.6f, 0.1f);
    }

    @Override
    public void tick(float delta) {

    }

}
