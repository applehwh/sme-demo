package sme.demo.ObserverJava9;

public class Observer {
    int i,b=2;
    Thread t = Thread.currentThread();
    static Integer integer= 0,o;
   
   
    public static void main(String[] args) throws InterruptedException {
        Observer.o=2;
        TestProcessor.testProcessor();
    }
}
