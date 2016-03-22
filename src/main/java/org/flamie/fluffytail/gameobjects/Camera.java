package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.shared.Tickable;

import static org.lwjgl.opengl.GL11.*;

public class Camera implements Drawable, Tickable {

    public class Position {
        float x;
        float y;
    }

    public interface FollowRule {
        Position getPosition(Entity e);
    }

    private FollowRule followRule;
    private Entity followEntity;
    private Position position;

    public Camera(Entity e, FollowRule r) {
        followEntity = e;
        followRule = r;
        position = followRule.getPosition(followEntity);
    }

    @Override
    public void draw() {
        glTranslatef(position.x, position.y, 0.0f);
    }

    @Override
    public void tick(float delta) {
        position = followRule.getPosition(followEntity);
    }

}
