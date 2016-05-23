package com.seven.demobox.designpatterns.patterns.observer;

public interface Subject {
    void registerObserver(Observer observer);
    void unRegisterObserver(Observer observer);
    void notifyObserver();
}
