package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.graphics.Sprite;
import org.flamie.fluffytail.input.Input;
import org.flamie.fluffytail.shared.Tickable;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

public class Furry implements Drawable, Tickable {

    private Sprite sprite;
    private Body body;

    public Furry(World world, Vec2 position) {
        sprite = new Sprite(Images.MORDA.getTexture());
        BodyDef def = new BodyDef();
        def.type = BodyType.DYNAMIC;
        def.position.set(position);
        body = world.createBody(def);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.1f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit
        Fixture fixture = body.createFixture(fixtureDef);
        body.setFixedRotation(true);
        body.setActive(true);
        Input.getPlayerMoveAxis().getAxisPublishSubject().subscribe(aFloat -> {
            body.setLinearVelocity(new Vec2(aFloat, 0.0f));
        });
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void draw() {
        Vec2 position = body.getPosition();
        sprite.draw(position.x - 0.2f, position.y - 0.1f, 0.4f, 0.2f);
    }

    @Override
    public void tick(float delta) {

    }

}
