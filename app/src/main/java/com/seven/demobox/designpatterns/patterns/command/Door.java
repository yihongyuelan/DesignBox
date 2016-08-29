package com.seven.demobox.designpatterns.patterns.command;

import static com.seven.demobox.designpatterns.patterns.command.CommandState.State.*;

public class Door extends CommandTarget {

    public void open() {
        mState.setDoorState(STATE_ON);
        update(mState);
    }

    public void closed() {
        mState.setDoorState(STATE_OFF);
        update(mState);
    }
}
