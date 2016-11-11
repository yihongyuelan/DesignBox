package com.seven.designbox.designpatterns.patterns.command;

public class CommandTarget {

    public static final CommandState mState = CommandState.getInstance();

    public void update(CommandState state) {
        CommandStateManager.getInstance().updateCommandState(state);
    }

}
