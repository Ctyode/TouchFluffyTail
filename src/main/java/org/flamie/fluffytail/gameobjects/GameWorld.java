package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.shared.Tickable;

import java.util.HashSet;
import java.util.Set;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class GameWorld implements Drawable, Tickable {

    private Set<Entity> entities;
    private World world;
    private Furry furry;

    public GameWorld() {
        world = new World(new Vec2(0.0f, -9.8f), false);
        entities = new HashSet<>();

        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        Body body = world.createBody(bd);
        furry = new Furry(body);
        furry.getBody().setTransform(new Vec2(0.5f, 0.5f), 0);
        furry.getBody().setFixedRotation(true);
    }

    @Override
    public void draw() {
        entities.forEach(Drawable::draw);
        furry.draw();
    }

    @Override
    public void tick(float delta) {
        world.step(delta, 8, 3);
        entities.forEach(e -> e.tick(delta));
    }

}
