package com.seven.demobox.designpatterns.patterns.command;

public class ControllerProxy {

    private CommandController mController;
    private volatile static ControllerProxy uniqueProxy;
    private Light mLight;
    private LightOnCommand mLightOnCommand;
    private LightOffCommand mLightOffCommand;
    private Door mDoor;
    private DoorOpenCommand mDoorOpenCommand;
    private DoorClosedCommand mDoorClosedCommand;
    private Fan mFan;
    private FanOnCommand mFanOnCommand;
    private FanOffCommand mFanOffCommand;
    private Stereo mStereo;
    private StereoStartCommand mStereoStartCommand;
    private StereoStopCommand mStereoStopCommand;

    private ControllerProxy() {
        if (mController == null) {
            mController = new CommandController();
            initCommands();
        }
    }

    public static ControllerProxy getInstance() {
        if (uniqueProxy == null) {
            synchronized (ControllerProxy.class) {
                if (uniqueProxy == null) {
                    uniqueProxy = new ControllerProxy();
                }
            }
        }
        return uniqueProxy;
    }

    private void initCommands() {
        //Light commands
        mLight = new Light();
        mLightOnCommand = new LightOnCommand(mLight);
        mLightOffCommand = new LightOffCommand(mLight);
        //Door commands
        mDoor = new Door();
        mDoorOpenCommand = new DoorOpenCommand(mDoor);
        mDoorClosedCommand = new DoorClosedCommand(mDoor);
        //Fan commands
        mFan = new Fan();
        mFanOnCommand = new FanOnCommand(mFan);
        mFanOffCommand = new FanOffCommand(mFan);
        //Stereo commands
        mStereo = new Stereo();
        mStereoStartCommand = new StereoStartCommand(mStereo);
        mStereoStopCommand = new StereoStopCommand(mStereo);
    }

    public void buttonOnClicked(String commandName) {
        Command command = null;
        switch (commandName) {
            case "Light":
                command = mLightOnCommand;
                break;
            case "Door":
                command = mDoorOpenCommand;
                break;
            case "Fan":
                command = mFanOnCommand;
                break;
            case "Stereo":
                command = mStereoStartCommand;
                break;
        }
        mController.setCommand(command);
        mController.buttonClicked();
    }

    public void buttonOffClicked(String commandName) {
        Command command = null;
        switch (commandName) {
            case "Light":
                command = mLightOffCommand;
                break;
            case "Door":
                command = mDoorClosedCommand;
                break;
            case "Fan":
                command = mFanOffCommand;
                break;
            case "Stereo":
                command = mStereoStopCommand;
                break;
        }
        mController.setCommand(command);
        mController.buttonClicked();
    }
}
