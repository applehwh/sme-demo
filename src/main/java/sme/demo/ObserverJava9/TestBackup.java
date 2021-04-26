package sme.demo.ObserverJava9;

import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestBackup {
    public static void testBackup() throws InterruptedException {
        //覆盖默认参数，修改缓冲池最大大小为2
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>(ForkJoinPool.commonPool(),2){
            @Override
            public int submit(Integer item) {
                System.out.println("生产数据："+item);
               return  super.submit(item);
            }
        };
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {
            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription sb) {
                this.subscription = sb;
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("----------------接收到数据：" + item);
                
                //为了模拟耗时操作： 这里sleep..
                try {
                    TimeUnit.MILLISECONDS.sleep(50L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("数据处理完了");
            }
        };

        // 3.发布者和订阅者 建立订阅关系
        publisher.subscribe(subscriber);

        //4.生产数据，并发布
        Random random = new Random();
        IntStream.rangeClosed(1,1000).forEach(data -> {
            publisher.submit(data);
        });

        //5.结束后，关闭发布者
        publisher.close();

        Thread.sleep(100000L);
    }
}
