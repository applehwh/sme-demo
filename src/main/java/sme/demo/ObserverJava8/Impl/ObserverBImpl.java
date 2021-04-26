package sme.demo.ObserverJava8.Impl;

import java.util.Observable;
import java.util.Observer;

public class ObserverBImpl implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Observer B :"+arg);
        
    }

}
