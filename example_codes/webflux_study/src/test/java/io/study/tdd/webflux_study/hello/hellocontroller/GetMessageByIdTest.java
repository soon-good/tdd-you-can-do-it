package io.study.tdd.webflux_study.hello.hellocontroller;

import io.study.tdd.webflux_study.hello.HelloController;
import io.study.tdd.webflux_study.hello.HelloService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = HelloController.class)
public class GetMessageByIdTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private HelloController helloController;

    @Test
    public void 일반적인_정상적인_요청에_대한_테스트(){
        webClient.get().uri("/hello/messages/1")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK);
    }
}
