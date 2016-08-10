package com.seven.demobox.designpatterns.patterns.command;

public class StereoStopCommand implements Command {

    private Stereo mStereo;

    public StereoStopCommand(Stereo stereo) {
        this.mStereo = stereo;
    }

    @Override
    public void execute() {
        mStereo.stop();
    }
}
