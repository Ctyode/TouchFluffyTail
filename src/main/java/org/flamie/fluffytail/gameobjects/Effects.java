package org.flamie.fluffytail.gameobjects;

import org.flamie.fluffytail.shared.Tickable;

public class Effects implements Tickable {

    private float autism = 0.0f;

    public void activateAutism() {
        autism = 10.0f;
    }

    public boolean isAutismActive() {
        return autism > 0.0f;
    }

    @Override
    public void tick(float delta) {
        autism -= delta;
    }

}
