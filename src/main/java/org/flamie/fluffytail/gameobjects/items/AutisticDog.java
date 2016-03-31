package org.flamie.fluffytail.gameobjects.items;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.gameobjects.GameWorld;
import org.flamie.fluffytail.graphics.Sprite;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class AutisticDog extends Item {

    public AutisticDog(GameWorld gameWorld, World world, Vec2 position) {
        super(gameWorld, world, position, new Sprite(Images.AUTISM.getTexture()));
        setOnPickup((gw, w, f) -> {
            f.getEffects().activateAutism();
            gw.removeEntity(this);
            w.destroyBody(getBody());
        });
    }

}
