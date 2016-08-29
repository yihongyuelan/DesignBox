package com.seven.demobox.designpatterns.patterns.command;

import static com.seven.demobox.designpatterns.patterns.command.CommandState.State.*;

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
