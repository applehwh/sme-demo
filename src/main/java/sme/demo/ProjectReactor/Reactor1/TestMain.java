package sme.demo.ProjectReactor.Reactor1;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static reactor.event.selector.Selectors.$;

public class TestMain {

    public static void main(String[] args) {
        Environment env = new Environment();
        Reactor reactors = Reactors.reactor()
                .env(env)
                .dispatcher(Environment.RING_BUFFER)
                .get();
        reactors.on($("channel"),event->System.out.println(event.getData()));
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                ()->reactors.notify("channel",Event.wrap("test")),
                0,100, TimeUnit.MILLISECONDS);

    }

}
