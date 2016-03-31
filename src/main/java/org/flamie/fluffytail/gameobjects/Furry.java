package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.graphics.Sprite;
import org.flamie.fluffytail.input.Input;
import org.flamie.fluffytail.shared.Tickable;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class Furry implements Drawable, Tickable, Collidable {

    public static final int autism = 1;

    private Sprite sprite;
    private Body body;
    private float movementVelocity;
    private float speed = 1.0f;
    private boolean landed = true;
    private Vec2 respawnPosition;
    private Consumer<Furry> onDieConsumer;
    private Set<Integer> effects;

    public Furry(World world, Vec2 position, Consumer<Furry> onDieConsumer) {
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
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        body.setFixedRotation(true);
        body.setActive(true);
        Input.getPlayerMoveAxis().getAxisPublishSubject().subscribe(f -> movementVelocity = f * speed);
        respawnPosition = position;
        effects = new HashSet<>();

        this.onDieConsumer = onDieConsumer;
    }

    public void applyEffect(int e) {
        effects.add(e);
    }

    public void purgeEffect(int e) {
        effects.remove(e);
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void beginContact(Collidable c) {
        if(c.getClass().equals(Floor.class)) {
            landed = true;
        }
    }

    @Override
    public void endContact(Collidable c) {
        if(c.getClass().equals(Floor.class)) {
            landed = false;
        }
    }

    @Override
    public void draw() {
        Vec2 position = body.getPosition();
        sprite.draw(position.x - 0.2f, position.y - 0.1f, 0.4f, 0.2f);
    }

    @Override
    public void tick(float delta) {
        if(movementVelocity != 0.0f) {
            if(effects.contains(autism)) {
                body.setLinearVelocity(new Vec2(-movementVelocity, body.getLinearVelocity().y));
            } else {
                body.setLinearVelocity(new Vec2(movementVelocity, body.getLinearVelocity().y));
            }
        }
        if(Input.isJumping() && landed) {
            body.applyLinearImpulse(new Vec2(0.0f, 0.04f), body.getPosition());
            landed = false;
        }
        if(body.getPosition().y < 0.0f) {
            body.setTransform(respawnPosition, 0.0f);
            onDieConsumer.accept(this);
        }

    }

}
