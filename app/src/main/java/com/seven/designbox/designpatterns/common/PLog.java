package com.seven.designbox.designpatterns.common;

import com.seven.designbox.BuildConfig;

public final class PLog {
    public static final void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(tag,msg);
        }
    }
}
