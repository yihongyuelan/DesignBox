package com.seven.designbox.designpatterns.patterns.command;

public class LightOnCommand implements Command {

    private Light mLight;

    public LightOnCommand(Light light) {
        this.mLight = light;
    }

    @Override
    public void execute() {
        mLight.turnOn();
    }
}
