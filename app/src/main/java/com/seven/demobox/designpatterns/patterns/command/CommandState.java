package com.seven.demobox.designpatterns.patterns.command;

public class CommandState {
    // 0 means state off, other means on
    public State mLightState;
    public State mDoorState;
    public State mFanState;
    public State mStereoState;

    private static CommandState mState;

    public static enum State {
        STATE_ON,
        STATE_OFF
    }

    private CommandState() {
    }

    public static CommandState getInstance() {
        if (mState == null) {
            synchronized (CommandState.class) {
                if (mState == null) {
                    mState = new CommandState();
                }
            }
        }
        return mState;
    }

    public State getLightState() {
        return mLightState;
    }

    public void setLightState(State lightState) {
        mLightState = lightState;
    }

    public State getDoorState() {
        return mDoorState;
    }

    public void setDoorState(State doorState) {
        mDoorState = doorState;
    }

    public State getFanState() {
        return mFanState;
    }

    public void setFanState(State fanState) {
        mFanState = fanState;
    }

    public State getStereoState() {
        return mStereoState;
    }

    public void setStereoState(State stereoState) {
        mStereoState = stereoState;
    }
}
