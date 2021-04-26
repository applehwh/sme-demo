package sme.demo.ProjectReactor.Reactor3;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
@Slf4j
public class TestMain {
    public static void main(String[] args)  {
        Flux.just("A","B","C")
                .subscribe(data-> log.info("onNext:{}",data),
                err->{},
                ()->log.info("onComplete"));
    }
}
