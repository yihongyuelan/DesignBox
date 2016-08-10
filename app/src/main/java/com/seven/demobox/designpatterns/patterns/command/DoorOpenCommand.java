package com.seven.demobox.designpatterns.patterns.command;

public class DoorOpenCommand implements Command {

    private Door mDoor;

    public DoorOpenCommand(Door door) {
        this.mDoor = door;
    }

    @Override
    public void execute() {
        mDoor.open();
    }
}
