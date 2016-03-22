package org.flamie.fluffy_tail.enums;

import org.flamie.fluffy_tail.Resources;

public enum Images {

    CURSOR ("res/images/cursor.png");

    private String path;
    private Integer texture;

    Images(String path) {
        this.path = path;
        texture = null;
    }

    public int getTexture() {
        if(texture == null) {
            texture = Resources.getTexture(path);
        }
        return texture;
    }

}
