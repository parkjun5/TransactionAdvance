package org.example.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootTest
public class InitTxTest {

    @Test
    void go() {
        //초기화 코드는 스프링이 초기화 시점에 호출한다.
        //하지만 @PostConstruct는 @Transactional과 함께 사용되면 트랜잭션이 적용되지 않는다.
        //초기화가 먼저 호출되고 다음에 AOP가 호출되기에 트랜잭션이 적용되지 않는다.
    }


    @TestConfiguration
    static class InitTestConfig {

        @Bean
        Hello helloInit() {
            return new Hello();
        }

    }


    static class Hello {

        @PostConstruct
        @Transactional
        public void initV1() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello Init @PostConstruct tx active={}", isActive);
        }

        @EventListener(ApplicationReadyEvent.class) //스프링이 완료되었을때
        @Transactional
        public void initV2() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello Init @EventListener tx active={}", isActive);
        }
    }


}
