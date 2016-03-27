package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.graphics.Drawable;
import org.flamie.fluffytail.shared.Tickable;
import org.jbox2d.common.Vec2;

import java.util.function.Function;

import static org.lwjgl.opengl.GL11.*;

public class Camera implements Drawable, Tickable {

    private Furry furry;
    private Function<Furry, Vec2> calculatePosition;
    private Vec2 position;

    public Camera(Furry furry, Function<Furry, Vec2> calculatePosition) {
        this.furry = furry;
        this.calculatePosition = calculatePosition;

        position = this.calculatePosition.apply(this.furry);
    }

    @Override
    public void draw() {
        glTranslatef(-position.x + 0.5f, 0.0f, 0.0f);
    }

    @Override
    public void tick(float delta) {
        position = calculatePosition.apply(furry);
    }
}
