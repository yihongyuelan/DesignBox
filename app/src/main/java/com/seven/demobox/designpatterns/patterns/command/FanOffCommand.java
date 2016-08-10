package com.seven.demobox.designpatterns.patterns.command;

public class FanOffCommand implements Command {

    private Fan mFan;

    public FanOffCommand(Fan fan) {
        this.mFan = fan;
    }

    @Override
    public void execute() {
        mFan.turnOff();
    }
}
