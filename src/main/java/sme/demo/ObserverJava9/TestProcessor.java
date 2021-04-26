package sme.demo.ObserverJava9;

import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class TestProcessor {
    public static void testProcessor() throws InterruptedException {
        Random random = new Random();
        //1.自定义发布者， 发布数据类型为Integer
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 2.定义处理器Processor
        MyProcessor processor = new MyProcessor();

        // 3.发布者和Processor建立关系
        publisher.subscribe(processor);

        //4.定义订阅者
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription sb) {
                //建立订阅关系
                this.subscription = sb;

                //2. 请求数据 ----
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("=============subscriber接收到数据：" + item);

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

        //5. Processor同订阅者建立关系
        processor.subscribe(subscriber);

        //4.生产数据，并发布
        publisher.submit(100);
        publisher.submit(200);
        publisher.submit(300);
        publisher.submit(400);

        //5.结束后，关闭发布者
        publisher.close();

        Thread.currentThread().join(1000);
    }
}
