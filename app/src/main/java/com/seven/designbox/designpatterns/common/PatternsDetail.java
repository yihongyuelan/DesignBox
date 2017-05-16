package com.seven.designbox.designpatterns.common;

import android.support.v7.app.AppCompatActivity;

public class PatternsDetail {
    public final int titleId;

    public final int desId;

    public final Class<? extends AppCompatActivity> patternClass;

    public PatternsDetail(
            int titleId, int descriptionId, Class<? extends AppCompatActivity> patternClass) {
        this.titleId = titleId;
        this.desId = descriptionId;
        this.patternClass = patternClass;
    }
}
