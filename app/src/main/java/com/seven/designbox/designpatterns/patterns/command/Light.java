package com.seven.designbox.designpatterns.patterns.command;

import static com.seven.designbox.designpatterns.patterns.command.CommandState.State.*;

public class Light extends CommandTarget {

    public void turnOn() {
        mState.setLightState(STATE_ON);
        update(mState);
    }

    public void turnOff() {
        mState.setLightState(STATE_OFF);
        update(mState);
    }
}
