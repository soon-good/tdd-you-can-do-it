package io.study.tdd.webflux_study.hello;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface HelloService {
    Optional<Mono<Message>> findById(Long id);
    Flux<Message> selectMessages();
}
