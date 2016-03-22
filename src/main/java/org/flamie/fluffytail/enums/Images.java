package org.flamie.fluffytail.enums;

import org.flamie.fluffytail.Resources;

import static org.lwjgl.opengl.GL11.*;

public enum Images {

    CURSOR ("res/images/cursor.png", GL_LINEAR),
    MORDA ("res/images/flamie_64x32.png", GL_NEAREST);

    private String path;
    private int filter;
    private Integer texture;

    Images(String path, int filter) {
        this.path = path;
        this.filter = filter;
        texture = null;
    }

    public int getTexture() {
        if(texture == null) {
            texture = Resources.getTexture(path, filter);
        }
        return texture;
    }

}
