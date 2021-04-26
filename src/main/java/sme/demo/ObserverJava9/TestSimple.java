package sme.demo.ObserverJava9;

import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class TestSimple {
    public static void test01() throws InterruptedException {
        Random random = new Random();
        //1.自定义发布者， 发布数据类型为Integer
        //使用jdk9自带的SubmissionPublisher,它实现了Publisher接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        //2.定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {
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
                System.out.println("接收到数据：" + item);

                this.subscription.request(2);
                //已经达到了目标，调用cancel告诉发布者，不在接收数据
//                this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                //出现异常
                throwable.printStackTrace();

                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                //在publisher.close();时触发..
                System.out.println("数据处理完了");
            }
        };

        // 3.发布者和订阅者 建立订阅关系
        publisher.subscribe(subscriber);

        //4.生产数据，并发布
        for (int i = 0; i < 1000; i++) {
            
            publisher.submit(i*100);
            publisher.submit(i*200);
            publisher.submit(i*300);
            publisher.submit(i*400);
        }

        //5.结束后，关闭发布者
        publisher.close();

        Thread.currentThread().join(1000);
    }
}
