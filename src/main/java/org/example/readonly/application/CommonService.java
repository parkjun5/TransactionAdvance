package org.example.readonly.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.readonly.domain.NonReadOnly;
import org.example.readonly.domain.ReadOnly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonService {

    private final ReadOnlyService readOnlyService;
    private final NonReadOnlyService nonReadOnlyService;

    public long bizMethodNonTran() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 트랜잭션 없음 시작 = {}", startTime);

        List<ReadOnly> all = readOnlyService.findAll();

        List<NonReadOnly> collect = all.stream()
                .map(n -> new NonReadOnly(n.getName() + UUID.randomUUID())).collect(Collectors.toList());

        nonReadOnlyService.insertAll(collect);
        long endTime = System.currentTimeMillis();

        long result = endTime - startTime;
        log.info("비지니스 로직 처리 시간 = {}", result);
        return result;
    }

    public long bizMethodNonTranWithFindAll() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 트랜잭션 없음 시작 = {}", startTime);

        List<ReadOnly> all = readOnlyService.findAllNoTran();

        List<NonReadOnly> collect = all.stream()
                .map(n -> new NonReadOnly(n.getName() + UUID.randomUUID())).collect(Collectors.toList());

        nonReadOnlyService.insertAll(collect);
        long endTime = System.currentTimeMillis();

        long result = endTime - startTime;
        log.info("비지니스 로직 처리 시간 = {}", result);
        return result;
    }

    @Transactional
    public long bizMethodTran() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 트랜잭션 없음 시작 = {}", startTime);

        List<ReadOnly> all = readOnlyService.findAll();

        List<NonReadOnly> collect = all.stream().map(n -> new NonReadOnly(n.getName() + UUID.randomUUID())).collect(Collectors.toList());

        nonReadOnlyService.insertAll(collect);
        long endTime = System.currentTimeMillis();

        long result = endTime - startTime;
        log.info("비지니스 로직 처리 시간 = {}", result);
        return result;
    }

}
