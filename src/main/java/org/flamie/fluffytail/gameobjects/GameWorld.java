package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.gameobjects.items.AutisticDog;
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
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

public class GameWorld implements Drawable, Tickable, ContactListener {

    private static GameWorld instance;

    private World world;
    private Camera camera;
    private Deque<Platform> platforms;
    private Platform[] startPlatforms;
    private Furry furry;
    private Set<Entity> entities;
    private float nextGeneratedPosition;

    public static GameWorld getInstance() {
        if(instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    private GameWorld() {
        world = new World(new Vec2(0.0f, -9.8f));
        world.setContactListener(this);
        platforms = new ArrayDeque<>();
        startPlatforms = new Platform[3];
        createStartPlatforms();

        nextGeneratedPosition = 1.0f;
        createPlatforms();

        entities = new HashSet<>();
        furry = new Furry(world, new Vec2(0.5f, 0.6f), f -> nextGeneratedPosition = 0.5f);
        entities.add(furry);
        entities.add(new AutisticDog(world, new Vec2(0.5f, 0.6f)));
        camera = new Camera(furry, f -> f.getBody().getPosition());
    }

    public World getB2DWorld() {
        return world;
    }

    public void deletePlatforms() {
        while(platforms.size() > 24) {
            Platform f = platforms.removeFirst();
            world.destroyBody(f.getBody());
        }
    }

    public void createPlatforms() {
        for(int j = 0; j < 4; j++) {
            float x = (float) Math.random();
            if (x < 0.35) {
                x = 0.1f;
            } else if (x > 0.35 && x < 0.7) {
                x = 0.5f;
            } else {
                x = 0.9f;
            }

            float y = (float) Math.random();
            if (y < 0.35) {
                y = 0.15f;
            } else if (y > 0.35 && y < 0.7) {
                y = 0.5f;
            } else {
                y = 0.8f;
            }

            x += nextGeneratedPosition;
            platforms.addLast(new Platform(world, new Vec2(x, y), 0.3f, 0.05f));
        }
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public void createStartPlatforms() {
        double a = Math.random();

        if(a < 0.35) {
            startPlatforms[0] = new Platform(world, new Vec2(0.1f, 0.15f), 0.3f, 0.05f);
            startPlatforms[1] = new Platform(world, new Vec2(0.5f, 0.5f), 0.3f, 0.05f);
            startPlatforms[2] = new Platform(world, new Vec2(0.9f, 0.8f), 0.3f, 0.05f);
        } else if(a > 0.35 && a < 0.7) {
            startPlatforms[0] = new Platform(world, new Vec2(0.1f, 0.8f), 0.3f, 0.05f);
            startPlatforms[1] = new Platform(world, new Vec2(0.5f, 0.5f), 0.3f, 0.05f);
            startPlatforms[2] = new Platform(world, new Vec2(0.9f, 0.8f), 0.3f, 0.05f);
        } else {
            startPlatforms[0] = new Platform(world, new Vec2(0.1f, 0.5f), 0.3f, 0.05f);
            startPlatforms[1] = new Platform(world, new Vec2(0.5f, 0.5f), 0.3f, 0.05f);
            startPlatforms[2] = new Platform(world, new Vec2(0.9f, 0.8f), 0.3f, 0.05f);
        }
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
        for (Platform p : startPlatforms) {
            p.draw();
        }
        platforms.forEach(Drawable::draw);
        entities.forEach(Drawable::draw);
    }

    @Override
    public void tick(float delta) {
        world.step(delta, 8, 3);
        camera.tick(delta);
        if(furry.getBody().getPosition().x > nextGeneratedPosition) {
            nextGeneratedPosition += 1.0f;
            createPlatforms();
            deletePlatforms();
        }
        entities.forEach(e -> e.tick(delta));
    }

}
