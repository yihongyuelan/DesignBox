package com.seven.designbox.designpatterns.patterns.observer;

public class CurrentConditionsDisplay implements Observer {
    @Override
    public void update(WeatherData data) {
        if (data != null) {
            android.util.Log.i("Seven","temperature="+data.temperature+"\n"
                                +"pressure="+data.pressure+"\n"
                                +"humidity="+data.humidity);
        }
    }
}
