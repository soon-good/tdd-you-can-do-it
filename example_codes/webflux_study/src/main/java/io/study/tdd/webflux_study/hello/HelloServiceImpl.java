package io.study.tdd.webflux_study.hello;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class HelloServiceImpl implements HelloService{

    @Override
    public Optional<Mono<Message>> findById(Long id) {
        Flux<Message> messageFlux = selectMessages();
        return Optional.ofNullable(messageFlux.filter(m -> m.getId().equals(id)).elementAt(0));
    }

    @Override
    public Flux<Message> selectMessages() {
        Message msg1 = Message.builder()
                .id(1L)
                .message("안녕하세요")
                .createdAt(LocalDateTime.of(LocalDate.now(), LocalTime.of(1, 2, 0)))
                .build();

        Message msg2 = Message.builder()
                .id(2L)
                .message("Hello")
                .createdAt(LocalDateTime.of(LocalDate.now(), LocalTime.of(2, 1, 0)))
                .build();

        return Flux.just(msg1, msg2);
    }
}
