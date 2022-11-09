package org.example.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.NonReadOnly;
import org.example.domain.ReadOnly;
import org.example.domain.repository.NonReadOnlyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class NonReadOnlyService {

    private final ReadOnlyService readOnlyService;
    private final NonReadOnlyRepository nonReadOnlyRepository;

    public List<NonReadOnly> findAll() {
        return nonReadOnlyRepository.findAll();
    }

    public void internalTest() {

        TransactionSynchronizationManager.setCurrentTransactionReadOnly(true);
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        log.info("internalTest method read only = {}", readOnly);

        List<ReadOnly> readOnlyList = readOnlyService.findAll();
        readOnlyList.forEach(ReadOnly::setRandomUUID);
        List<NonReadOnly> nonReadOnlyList = nonReadOnlyRepository.findAll();
        nonReadOnlyList.forEach(NonReadOnly::setRandomUUID);
        log.info("readOnly로 불러온 리스트는 더티체킹되지 않고");
        log.info("nonReadOnlyList는 더티체킹이 되어야 할 것 같다");

    }

}
