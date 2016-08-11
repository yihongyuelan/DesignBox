package com.seven.demobox.designpatterns.patterns.command;

public class CommandStateManager {

    private CommandStateListener mListener;

    public CommandStateManager(CommandStateListener listener) {
        this.mListener = listener;
    }

    public void updateCommandState(CommandState state) {
        mListener.onStateChanged(state);
    }
}
