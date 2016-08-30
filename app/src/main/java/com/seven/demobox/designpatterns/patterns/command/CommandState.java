package com.seven.demobox.designpatterns.patterns.command;

public class CommandState {
    public State mLightState = null;
    public State mDoorState = null;
    public State mFanState = null;
    public State mStereoState = null;

    private static CommandState mState;

    public enum State {
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

    public void onRelease() {
        mLightState = null;
        mDoorState = null;
        mFanState = null;
        mStereoState = null;
    }
}
