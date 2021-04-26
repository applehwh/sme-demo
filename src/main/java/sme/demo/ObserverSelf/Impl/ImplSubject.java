package sme.demo.ObserverSelf.Impl;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sme.demo.ObserverSelf.interfaces.Observer;
import sme.demo.ObserverSelf.interfaces.Subject;

public class ImplSubject implements Subject<String> {
    private final Set<Observer<String>> observers=new CopyOnWriteArraySet<>();
    private final ExecutorService executorService=Executors.newCachedThreadPool();

    @Override
    public void registerObserver(Observer<String> observer) {
        observers.add(observer);

    }

    @Override
    public void unregisterObserver(Observer<String> observer) {
        observers.remove(observer);

    }

    @Override
    public void notifyObservers(String event) {
        observers.forEach(observer->executorService.submit(()->observer.observe(event)));

    }

}
