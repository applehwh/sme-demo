package sme.demo.Rxjava.Rxjava2;

import java.util.concurrent.Flow.Publisher;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RxjavaMain2 {

    public static void main(String[] args) {
        //第一步：创建连载小说（被观察者）
        Observable observable=Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext("连载1");
                emitter.onNext("连载2");
                emitter.onNext("连载3");
                emitter.onComplete();
                
            }
        }); 
//创建读者（观察者）
        Observer<String> reader=new Observer<String>() {
            Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable=d;
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String value) {
                System.out.println("onNext:"+value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError="+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete()");
                
            }
        };
        observable.subscribe(reader);
    }
   
}
