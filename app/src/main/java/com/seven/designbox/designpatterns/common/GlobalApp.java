package com.seven.designbox.designpatterns.common;

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
//        try {
//            Class<?> cls = AutoLayoutConifg.class;
//            Field f = cls.getDeclaredField("useDeviceSize");
//            f.setAccessible(true);//为 true 则表示反射的对象在使用时取消 Java 语言访问检查
//            System.out.println(f.get(AutoLayoutConifg.getInstance()));
//            f.set(AutoLayoutConifg.getInstance(), false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
