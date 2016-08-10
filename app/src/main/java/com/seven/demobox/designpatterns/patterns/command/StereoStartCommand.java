package com.seven.demobox.designpatterns.patterns.command;

public class StereoStartCommand implements Command {

    private Stereo mStereo;

    public StereoStartCommand(Stereo stereo) {
        this.mStereo = stereo;
    }

    @Override
    public void execute() {
        mStereo.start();
    }
}
