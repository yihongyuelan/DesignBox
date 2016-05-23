package com.seven.demobox.designpatterns.common;

public final class Log {
    private static final boolean DEBUGABLE = true;
    public static final void i(String tag, String msg) {
        if (DEBUGABLE) {
            android.util.Log.i(tag,msg);
        }
    }
}
