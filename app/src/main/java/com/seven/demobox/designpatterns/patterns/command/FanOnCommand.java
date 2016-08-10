package com.seven.demobox.designpatterns.patterns.command;

public class FanOnCommand implements Command {

    private Fan mFan;

    public FanOnCommand(Fan fan) {
        this.mFan = fan;
    }

    @Override
    public void execute() {
        mFan.turnOn();
    }
}
