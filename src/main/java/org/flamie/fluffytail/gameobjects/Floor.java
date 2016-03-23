package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.graphics.Graphics;
import org.flamie.fluffytail.graphics.Sprite;
import org.flamie.fluffytail.shared.Tickable;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.awt.*;

public class Floor implements Drawable, Tickable, Collidable {

    private Sprite sprite;
    private Body body;
    private float width;
    private float height;

    public Floor(World world, Vec2 position, float width, float height) {
        this.width = width;
        this.height = height;

        sprite = new Sprite(Images.MISSING_TEXTURE.getTexture());
        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        def.position.set(position);
        body = world.createBody(def);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width / 2, height / 2);
        Fixture fixture = body.createFixture(polygonShape, 2.0f);
        fixture.setUserData(this);
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void beginContact(Collidable c) {
        /* whatever */
    }

    @Override
    public void endContact(Collidable c) {
        /* whatever */
    }

    @Override
    public void draw() {
        Vec2 position = body.getPosition();
        sprite.draw(position.x - width / 2, position.y - height / 2, width, height);
    }

    @Override
    public void tick(float delta) {

    }

}
