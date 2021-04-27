package sme.demo.Rxjava.Rxjava1;

import io.reactivex.disposables.Disposable;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class RxjavaMain1 {

    public static void main(String[] args) {
//        //第一步：创建连载小说（被观察者）
//        Observable observable=Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> emitter) {
//                emitter.onNext("连载1");
//                emitter.onNext("连载2");
//                emitter.onNext("连载3");
//                emitter.onCompleted();
//
//            }
//        });
////创建读者（观察者）
//        Observer<String> reader=new Observer<String>() {
//            @Override
//            public void onNext(String value) {
//                System.out.println("onNext:"+value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onError="+e.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("onComplete()");
//
//            }
//        };
//        observable.subscribe(reader);

       Observable.just(1,2,3,4,5,6,7,8,9).forEach(System.out::print);
       System.out.println("");
        Observable.just(1,2,3,4,5,6,7,8,9).map(i->i*2).forEach(System.out::print);
        System.out.println("");
        Observable.just(1,2,3,4,5,6,7,8,9).map(i->i*2).filter(i->i>10).forEach(System.out::print);
        System.out.println("");
        Observable.just(1,2,3,4,5,6,7,8,9).map(i->i*2).filter(i->i>10).count().subscribe(System.out::print);
        System.out.println("");
        Observable.zip(
                Observable.just("A","B","C"),
                Observable.just("1，","2，","3，"),
                (x,y)->x+y).forEach(System.out::print);
    }

}
