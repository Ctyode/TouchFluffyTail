package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.shared.Tickable;

import java.util.HashSet;
import java.util.Set;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

public class GameWorld implements Drawable, Tickable, ContactListener {

    private World world;
    private Furry furry;
    private Floor[] floor;
    private float x;
    private float y;

    public GameWorld() {
        world = new World(new Vec2(0.0f, -9.8f));
        world.setContactListener(this);
        floor = new Floor[8];

        for(int j = 0; j < floor.length; j++) {
            x = (float) Math.random();
            if (x < 0.35) {
                x = 0.1f;
            } else if (x > 0.35 && x < 0.7) {
                x = 0.5f;
            } else {
                x = 0.9f;
            }

            y = (float) Math.random();
            if (y < 0.35) {
                y = 0.15f;
            } else if (y > 0.35 && y < 0.7) {
                y = 0.5f;
            } else {
                y = 0.8f;
            }

            floor[j] = new Floor(world, new Vec2(x, y), 0.3f, 0.05f);
        }

        furry = new Furry(world, new Vec2(x, y + 0.1f));
    }

    @Override
    public void beginContact(Contact contact) {
        Collidable a = (Collidable) contact.getFixtureA().getUserData();
        Collidable b = (Collidable) contact.getFixtureB().getUserData();
        a.beginContact(b);
        b.beginContact(a);
    }

    @Override
    public void endContact(Contact contact) {
        Collidable a = (Collidable) contact.getFixtureA().getUserData();
        Collidable b = (Collidable) contact.getFixtureB().getUserData();
        a.endContact(b);
        b.endContact(a);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) { /* whatever */ }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) { /* whatever */ }

    @Override
    public void draw() {
        furry.draw();
        for (Floor f : floor) {
            f.draw();
        }
    }

    @Override
    public void tick(float delta) {
        world.step(delta, 8, 3);
        furry.tick(delta);
        for (Floor f : floor) {
            f.tick(delta);
        }
    }

}
