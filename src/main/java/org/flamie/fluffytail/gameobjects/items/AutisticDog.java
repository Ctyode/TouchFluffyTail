package org.flamie.fluffytail.gameobjects.items;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.gameobjects.GameWorld;
import org.flamie.fluffytail.graphics.Sprite;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class AutisticDog extends Item {

    public AutisticDog(World world, Vec2 position) {
        super(world, position, new Sprite(Images.AUTISM.getTexture()));
        setOnPickup((f) -> {
            f.getEffects().activateAutism();
            GameWorld.getInstance().removeEntity(this);
            GameWorld.getInstance().getB2DWorld().destroyBody(getBody());
        });
    }

}
