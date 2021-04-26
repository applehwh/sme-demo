package sme.demo.ObserverSelf;

import sme.demo.ObserverSelf.Impl.ImplSubject;
import sme.demo.ObserverSelf.Impl.ObserverAImpl;
import sme.demo.ObserverSelf.Impl.ObserverBImpl;
import sme.demo.ObserverSelf.interfaces.Observer;
import sme.demo.ObserverSelf.interfaces.Subject;

public class TestMain {

    public static void main(String[] args) {
        Subject<String> subject=new ImplSubject();
        Observer observerA=new ObserverAImpl();
        Observer observerB=new ObserverBImpl();
        Observer observerC=new ObserverAImpl();
        
        subject.notifyObservers("No listeners");
        
        subject.registerObserver(observerA);
        subject.notifyObservers("Message for A");
        
        subject.registerObserver(observerB);
        subject.notifyObservers("Message for A&B");
        
        subject.registerObserver(observerC);
        subject.notifyObservers("Message for A&B");

    }

}
