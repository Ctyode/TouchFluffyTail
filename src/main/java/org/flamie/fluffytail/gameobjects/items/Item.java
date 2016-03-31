package org.flamie.fluffytail.gameobjects.items;

import org.flamie.fluffytail.gameobjects.Collidable;
import org.flamie.fluffytail.gameobjects.Entity;
import org.flamie.fluffytail.gameobjects.Furry;
import org.flamie.fluffytail.graphics.Sprite;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.util.function.Consumer;

public class Item extends Entity {

    private Sprite sprite;
    private Body body;
    private Consumer<Furry> onPickup;

    public Item(World world, Vec2 position, Sprite sprite) {
        this.sprite = sprite;

        BodyDef def = new BodyDef();
        def.type = BodyType.DYNAMIC;
        def.position.set(position);
        body = world.createBody(def);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.05f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        body.setFixedRotation(true);
        body.setActive(true);
    }

    public Body getBody() {
        return body;
    }

    public void setOnPickup(Consumer<Furry> onPickup) {
        this.onPickup = onPickup;
    }

    @Override
    public void beginContact(Collidable c) {
        if(c.getClass().equals(Furry.class)) {
            if(onPickup != null) {
                onPickup.accept((Furry) c);
            }
        }
    }

    @Override
    public void endContact(Collidable c) {

    }

    @Override
    public void draw() {
        Vec2 position = body.getPosition();
        sprite.draw(position.x - 0.1f, position.y - 0.05f, 0.1f, 0.1f);
    }

    @Override
    public void tick(float delta) {

    }

}
