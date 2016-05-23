package com.seven.demobox.designpatterns.patterns.observer;

import java.util.ArrayList;
import java.util.Observable;

public class WeatherStation extends Observable implements Subject {
    private WeatherData mData;
    private ArrayList<Observer> mObservers;

    public WeatherStation() {
        mObservers = new ArrayList<>();
    }

    public void setMeasurements(WeatherData data) {
        this.mData = data;
        setChanged();
        notifyObservers(data);
//        measurementsChanged();
    }


    private void measurementsChanged() {
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        mObservers.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer observer) {
        final int index = mObservers.indexOf(observer);
        if (index >= 0) {
            mObservers.remove(index);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer: mObservers) {
            observer.update(mData);
        }
    }

}
