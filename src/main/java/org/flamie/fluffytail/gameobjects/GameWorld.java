package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.shared.Tickable;

import java.util.ArrayDeque;
import java.util.Deque;
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
    private Camera camera;
    private Furry furry;
    private Deque<Floor> floor;
    private Floor[] startPlatforms;
    private float x;
    private float y;
    private float nextGeneratedPosition;

    public GameWorld() {
        world = new World(new Vec2(0.0f, -9.8f));
        world.setContactListener(this);
        floor = new ArrayDeque<>();
        startPlatforms = new Floor[3];
        createStartPlatforms();

        nextGeneratedPosition = 1.0f;
        createPlatforms();

        furry = new Furry(world, new Vec2(0.5f, 0.6f), f -> nextGeneratedPosition = 1.0f);
        camera = new Camera(furry, f -> f.getBody().getPosition());
    }

    public void deletePlatforms() {
        while(floor.size() > 12) {
            Floor f = floor.removeFirst();
            world.destroyBody(f.getBody());
        }
    }

    public void createPlatforms() {
        for(int j = 0; j < 4; j++) {
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

            x += nextGeneratedPosition;
            floor.addLast(new Floor(world, new Vec2(x, y), 0.3f, 0.05f));
        }
    }

    public void createStartPlatforms() {
        startPlatforms[0] = new Floor(world, new Vec2(0.1f, 0.15f), 0.3f, 0.05f);
        startPlatforms[1] = new Floor(world, new Vec2(0.5f, 0.5f), 0.3f, 0.05f);
        startPlatforms[2] = new Floor(world, new Vec2(0.9f, 0.8f), 0.3f, 0.05f);
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
        camera.draw();
        furry.draw();
        for (Floor s : startPlatforms) {
            s.draw();
        }
        for (Floor f : floor) {
            f.draw();
        }
    }

    @Override
    public void tick(float delta) {
        world.step(delta, 8, 3);
        furry.tick(delta);
        camera.tick(delta);
        if(furry.getBody().getPosition().x > nextGeneratedPosition) {
            nextGeneratedPosition += 1.0f;
            createPlatforms();
            deletePlatforms();
        }
        for (Floor s : startPlatforms) {
            s.tick(delta);
        }
        for (Floor f : floor) {
            f.tick(delta);
        }
    }

}
