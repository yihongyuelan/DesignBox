package com.seven.demobox.designpatterns.patterns.command;

public class LightOffCommand implements Command {

    private Light mLight;

    public LightOffCommand(Light light) {
        this.mLight = light;
    }

    @Override
    public void execute() {
        mLight.turnOff();
    }
}
