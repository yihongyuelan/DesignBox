package com.seven.demobox.designpatterns.common;

import android.support.v7.app.AppCompatActivity;

public class PatternDetails {
    public final int patterTitleId;

    public final int patternDescriptionId;

    public final Class<? extends AppCompatActivity> patternActivityClass;

    public PatternDetails(
            int titleId, int descriptionId, Class<? extends AppCompatActivity> activityClass) {
        this.patterTitleId = titleId;
        this.patternDescriptionId = descriptionId;
        this.patternActivityClass = activityClass;
    }
}
