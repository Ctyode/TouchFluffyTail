package org.flamie.fluffytail.gameobjects;

public interface Collidable {

    void beginContact(Collidable c);
    void endContact(Collidable c);

}
