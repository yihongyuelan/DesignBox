package com.seven.demobox.designpatterns.patterns.command;

import static com.seven.demobox.designpatterns.patterns.command.CommandState.State;

public class Door extends CommandTarget {

    public void open() {
        update(State.STATE_ON);
    }

    public void closed() {
        update(State.STATE_OFF);
    }
}
