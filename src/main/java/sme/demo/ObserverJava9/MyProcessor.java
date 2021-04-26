package sme.demo.ObserverJava9;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String>{
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription sb) {
        //建立订阅关系
        this.subscription = sb;
        //2. 请求数据 ----
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        System.out.println("processor接收到数据：" + item);
        
        //过滤掉小于200的数据， 
        if(item > 200) {
            //将消息转换String
            this.submit("String MSG-" + item);
        }

        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        //出现异常
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("Processor:数据处理完了");
    }
}
