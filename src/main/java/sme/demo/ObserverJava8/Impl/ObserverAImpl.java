package sme.demo.ObserverJava8.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class ObserverAImpl implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        List list=new ArrayList(List.of("a","b","c"));
        System.out.println("Observer B :"+arg);
        
    }

}
