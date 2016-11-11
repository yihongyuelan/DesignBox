package com.seven.designbox.designpatterns.patterns.command;

public class CommandController {
    private Command mCommand;

    public void setCommand(Command command) {
        this.mCommand = command;
    }

    public void buttonClicked() {
        mCommand.execute();
    }
}
