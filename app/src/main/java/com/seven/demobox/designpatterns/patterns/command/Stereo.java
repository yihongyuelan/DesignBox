package com.seven.demobox.designpatterns.patterns.command;

import static com.seven.demobox.designpatterns.patterns.command.CommandState.State;

public class Stereo extends CommandTarget {

    public void start() {
        update(State.STATE_ON);
    }

    public void stop() {
        update(State.STATE_OFF);
    }
}
