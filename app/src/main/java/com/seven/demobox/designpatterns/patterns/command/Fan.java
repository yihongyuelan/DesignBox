package com.seven.demobox.designpatterns.patterns.command;

import static com.seven.demobox.designpatterns.patterns.command.CommandState.State;

public class Fan extends CommandTarget {

    public void turnOn() {
        update(State.STATE_ON);
    }

    public void turnOff() {
        update(State.STATE_OFF);
    }
}
