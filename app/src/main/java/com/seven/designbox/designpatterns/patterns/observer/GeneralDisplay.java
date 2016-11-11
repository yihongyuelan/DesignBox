package com.seven.designbox.designpatterns.patterns.observer;

import java.util.*;

public class GeneralDisplay implements java.util.Observer {
    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof WeatherData) {
            final WeatherData data = (WeatherData)o;
            android.util.Log.i("Seven","Java_temperature="+data.temperature+"\n"
                    +"Java_pressure="+data.pressure+"\n"
                    +"Java_humidity="+data.humidity);
        }
    }
}
