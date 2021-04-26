package sme.demo.ObserverJava8.Impl;

import java.util.Observable;
import java.util.Observer;


public class TestMain {

    public static void main(String[] args) {
        Observable subject = new Observable();
        Observer observerA = new ObserverAImpl();
        Observer observerB = new ObserverBImpl();
        Observer observerC = new ObserverAImpl();

        subject.notifyObservers("No listeners");

        subject.addObserver(observerA);
        subject.notifyObservers("Message for A");

        subject.addObserver(observerB);
        subject.notifyObservers("Message for A&B");

        subject.addObserver(observerC);
        subject.notifyObservers("Message for A&B");

    }
}

