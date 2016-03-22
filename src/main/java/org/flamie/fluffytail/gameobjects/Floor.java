package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.graphics.Sprite;
import org.flamie.fluffytail.shared.Tickable;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class Floor implements Drawable, Tickable {

    private Sprite sprite;
    private Body body;

    public Floor(World world, Vec2 position) {
        sprite = new Sprite(Images.MISSING_TEXTURE.getTexture());
        BodyDef def = new BodyDef();
        def.type = BodyType.STATIC;
        def.position.set(position);
        body = world.createBody(def);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f, 0.1f);
        body.createFixture(polygonShape, 2.0f);
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
