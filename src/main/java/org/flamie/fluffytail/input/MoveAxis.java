package org.flamie.fluffytail.input;

import rx.subjects.PublishSubject;

public class MoveAxis {

    private PublishSubject<Float> axisPublishSubject;
    private float lastAxisValue;
    private boolean movePositive;
    private boolean moveNegative;

    public MoveAxis() {
        axisPublishSubject = PublishSubject.create();
        lastAxisValue = 0.0f;
    }

    public PublishSubject<Float> getAxisPublishSubject() {
        return axisPublishSubject;
    }

    public float getAxis() {
        return lastAxisValue;
    }

    public void startMovingPositive() {
        movePositive = true;
        lastAxisValue = 1.0f;
        axisPublishSubject.onNext(lastAxisValue);
    }

    public void stopMovingPositive() {
        movePositive = false;
        if(!moveNegative) {
            lastAxisValue = 0.0f;
            axisPublishSubject.onNext(lastAxisValue);
        }
    }

    public void startMovingNegative() {
        moveNegative = true;
        lastAxisValue = -1.0f;
        axisPublishSubject.onNext(lastAxisValue);
    }

    public void stopMovingNegative() {
        moveNegative = false;
        if(!movePositive) {
            lastAxisValue = 0.0f;
            axisPublishSubject.onNext(lastAxisValue);
        }
    }

}
