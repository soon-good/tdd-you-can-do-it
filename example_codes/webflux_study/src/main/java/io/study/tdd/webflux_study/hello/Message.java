package io.study.tdd.webflux_study.hello;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Message {
    private Long id;
    private LocalDateTime createdAt;
    private String message;

    @Builder
    public Message(Long id, LocalDateTime createdAt, String message){
        this.id = id;
        this.createdAt = createdAt;
        this.message = message;
    }
}
