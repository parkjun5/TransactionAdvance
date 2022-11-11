package org.example.readonly.application;

import lombok.extern.slf4j.Slf4j;
import org.example.readonly.domain.NonReadOnly;
import org.example.readonly.domain.ReadOnly;
import org.example.readonly.domain.repository.NonReadOnlyRepository;
import org.example.readonly.domain.repository.ReadOnlyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class CommonServiceTest {

    @Autowired NonReadOnlyRepository nonReadOnlyRepository;
    @Autowired ReadOnlyRepository readOnlyRepository;

    @Autowired CommonService commonService;

    @BeforeEach
    void initDB() {

        List<ReadOnly> readOnlyList = IntStream.range(0, 500000).mapToObj(this::createReadOnly).collect(Collectors.toList());
        readOnlyRepository.saveAll(readOnlyList);
    }


    @Test
    void isTransactionNeedForReadTest() {
        List<Long> results = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            long tranResult = commonService.bizMethodTran();
            long nonTranResult = commonService.bizMethodNonTranWithFindAll();

            log.info("전체 트랜잭션의 로직처리 속도 = {}", tranResult);
            log.info("find에 트랜잭션이 없는 로직처리 속도 = {}", nonTranResult);
            long diff = tranResult - nonTranResult;
            log.info("두 로직 처리 속도 비교 tran - noTran = {}", diff);
            results.add(diff);
        }

        results.forEach(result -> log.info("결과 : {}", result));
        AtomicLong sum = new AtomicLong(0L);
        results.forEach(sum::addAndGet);
        long avg = sum.longValue() / 10;
        log.info("평균 차이 : {}", avg);
    }


    @Test
    void transactionTest() {
        List<Long> results = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            long tranResult = commonService.bizMethodTran();
            long nonTranResult = commonService.bizMethodNonTran();

            log.info("전체 트랜잭션의 로직처리 속도 = {}", tranResult);
            log.info("세부 트랜잭션의 로직처리 속도 = {}", nonTranResult);
            long diff = tranResult - nonTranResult;
            log.info("두 로직 처리 속도 비교 tran - noTran = {}", diff);
            results.add(diff);
        }

        results.forEach(result -> log.info("결과 : {}", result));
        AtomicLong sum = new AtomicLong(0L);
        results.forEach(sum::addAndGet);
        long avg = sum.longValue() / 20;
        log.info("평균 차이 : {}", avg);
    }

    private NonReadOnly createNonReadOnly(int index) {
        return new NonReadOnly("변경 가능_" + index);
    }

    private ReadOnly createReadOnly(int index) {
        return new ReadOnly("읽기 전용_" + index);
    }
}