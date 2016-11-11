package com.seven.designbox.designpatterns.patterns.command;

public class DoorClosedCommand implements Command {
    private Door mDoor;

    public DoorClosedCommand(Door door) {
        this.mDoor = door;
    }

    @Override
    public void execute() {
        mDoor.closed();
    }
}
