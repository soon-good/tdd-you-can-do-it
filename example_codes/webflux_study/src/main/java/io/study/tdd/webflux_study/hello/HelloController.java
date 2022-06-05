package io.study.tdd.webflux_study.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }

    @GetMapping("/hello/messages")
    public Flux<Message> getMessages(){
        return helloService.selectMessages();
    }

    @GetMapping("/hello/messages/{id}")
    public Mono<Message> getMessageById(@PathVariable("id") final String id){
        if(Optional.ofNullable(id).isEmpty()) return Mono.empty();
        if(id.isEmpty()) return Mono.empty();

        return helloService
                .findById(Long.parseLong(id))
                .orElse(Mono.empty());
    }

}
