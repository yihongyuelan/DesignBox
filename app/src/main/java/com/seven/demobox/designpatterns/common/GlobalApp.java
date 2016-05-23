package com.seven.demobox.designpatterns.common;

import android.app.Application;
import android.content.Context;

public class GlobalApp extends Application {
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        if (sContext == null) {
            sContext = getApplicationContext();
        }
    }
}
