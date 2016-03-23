package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.shared.Tickable;

import java.util.HashSet;
import java.util.Set;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class GameWorld implements Drawable, Tickable {

    private Set<Entity> entities;
    private World world;
    private Furry furry;
    private Floor floor;

    public GameWorld() {
        world = new World(new Vec2(0.0f, -0.2f));
        entities = new HashSet<>();

        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        Body box = world.createBody(def);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(0.6f, 0.1f);
        box.createFixture(poly, 2.0f);
        floor = new Floor(box);
        floor.getBody().setTransform(new Vec2(0.5f, 0.1f), 0);


        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        Body furryBody = world.createBody(bd);
        furry = new Furry(furryBody);
        furry.getBody().setTransform(new Vec2(0.5f, 0.5f), 0);
        furry.getBody().setFixedRotation(true);
        furry.getBody().setActive(true);
    }

    @Override
    public void draw() {
        entities.forEach(Drawable::draw);
        furry.draw();
        floor.draw();
    }

    @Override
    public void tick(float delta) {
        world.step(delta, 8, 3);
        entities.forEach(e -> e.tick(delta));
    }

}
