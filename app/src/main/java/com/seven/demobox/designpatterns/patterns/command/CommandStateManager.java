package com.seven.demobox.designpatterns.patterns.command;

public class CommandStateManager {

    private CommandStateListener mListener;
    private static CommandStateManager mManager;

    private CommandStateManager() {
    }

    public static CommandStateManager getInstance() {
        if (mManager == null) {
            synchronized (CommandStateListener.class) {
                if (mManager == null) {
                    mManager = new CommandStateManager();
                }
            }
        }
        return mManager;
    }

    public void updateCommandState(CommandState state) {
        if (mListener != null) {
            mListener.onStateChanged(state);
        }
    }

    public void setStateListener(CommandStateListener listener) {
        mListener = listener;
    }
}
