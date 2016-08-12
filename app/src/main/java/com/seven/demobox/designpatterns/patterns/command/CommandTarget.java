package com.seven.demobox.designpatterns.patterns.command;

import static com.seven.demobox.designpatterns.patterns.command.CommandState.State;

public class CommandTarget {
    public static final CommandState mState = CommandState.getInstance();
    public static final CommandStateManager mManager = CommandStateManager.getInstance();

    public void update(State state) {
        mState.setLightState(state);
        mManager.updateCommandState(mState);
    }
}
