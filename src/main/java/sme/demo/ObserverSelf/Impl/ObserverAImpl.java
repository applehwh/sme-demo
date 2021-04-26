package sme.demo.ObserverSelf.Impl;

import sme.demo.ObserverSelf.interfaces.Observer;

public class ObserverAImpl implements Observer<String> {

    @Override
    public void observe(String event) {
        System.out.println("Observer A :"+event);
        
    }

}
