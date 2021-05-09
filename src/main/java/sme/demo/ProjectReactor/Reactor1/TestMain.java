package sme.demo.ProjectReactor.Reactor1;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static reactor.event.selector.Selectors.$;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        Environment env = new Environment();
        Reactor reactors = Reactors.reactor()
               // .env(env)
                //Dispatcher：用于管理EventHandler，分发event的容器，也是一个事件处理调度器
               // .dispatcher(Environment.EVENT_LOOP)
                .get();
        //注册事件处理器 EventHandler event->System.out.println(event.getData())
        //Selector: 事件轮循选择器，selector主要实现了轮循队列中的事件状态，取出当前能够处理的状态
        reactors.on($("channel"),event->System.out.println(event.getData()));
        //Event.wrap("test") Handle 事件
//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
//                ()->reactors.notify("channel",Event.wrap("test")),
//                0,100, TimeUnit.MILLISECONDS);
        for (int i = 0; i < 100; i++) {
            reactors.notify("channel",new Event<String>("test"));
        }
     //   Thread.sleep(1000);
       // reactors.send()


    }

}
