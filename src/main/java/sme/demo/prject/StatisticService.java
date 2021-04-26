package sme.demo.prject;


import reactor.core.publisher.Flux;

public class StatisticService {
private final UserRepository userRepository;
private final  MessageRepository messageRepository;

    public StatisticService() {
        this.messageRepository = new MessageRepository();
        this.userRepository = new UserRepository();
    }

    public Flux<UsersTatistic> updateStatisic(Flux<ChatMessage> messagesFlux){
        return messagesFlux
                .map(MessageMapper::toDomainUnit)
                .transform(messageRepository::saveAll)
               ;// .retryBackoff()
    }
}
