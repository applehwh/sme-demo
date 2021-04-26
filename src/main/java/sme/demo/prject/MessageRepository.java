package sme.demo.prject;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class MessageRepository {
    public Flux<UsersTatistic> saveAll(Publisher publisher){
        return Flux.empty();
    }
}
