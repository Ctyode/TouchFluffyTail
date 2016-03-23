package org.flamie.fluffytail.input;

import org.jbox2d.common.Vec2;
import rx.subjects.PublishSubject;

public class MoveAxis {

    private PublishSubject<Vec2> axisPublishSubject;
    private float lastAxisValue;
    private boolean movePositive;
    private boolean moveNegative;

    public MoveAxis() {
        axisPublishSubject = PublishSubject.create();
        lastAxisValue = 0.0f;
    }

    public PublishSubject<Vec2> getAxisPublishSubject() {
        return axisPublishSubject;
    }

    public float getAxis() {
        return lastAxisValue;
    }

    public void startMovingPositive() {
        movePositive = true;
        lastAxisValue = 1.0f;
    }

    public void stopMovingPositive() {
        movePositive = false;
        if(!moveNegative) {
            lastAxisValue = 0.0f;
        }
    }

    public void startMovingNegative() {
        moveNegative = true;
        lastAxisValue = 0.0f;
    }

    public void stopMovingNegative() {
        moveNegative = false;
        if(!movePositive) {
            lastAxisValue = 0.0f;
        }
    }

}
