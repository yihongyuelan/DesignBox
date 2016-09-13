package com.seven.demobox.designpatterns.common;

import com.seven.demobox.BuildConfig;

public final class PLog {
    public static final void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag,msg);
        }
    }
}
