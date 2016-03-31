package org.flamie.fluffytail.gameobjects.items;

import org.flamie.fluffytail.enums.Images;
import org.flamie.fluffytail.gameobjects.Furry;
import org.flamie.fluffytail.graphics.Sprite;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import java.util.Timer;
import java.util.TimerTask;

public class AutisticDog extends Item {

    public AutisticDog(World world, Vec2 position) {
        super(world, position, new Sprite(Images.AUTISM.getTexture()));
        setOnTouch((w, f) -> {
            System.out.println("Oh, no, it's retarded!");
            f.applyEffect(Furry.autism);
            w.destroyBody(getBody());

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    f.purgeEffect(Furry.autism);
                }
            }, 10000);
        });
    }

}
