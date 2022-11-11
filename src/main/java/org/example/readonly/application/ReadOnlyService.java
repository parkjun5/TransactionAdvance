package org.example.readonly.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.readonly.domain.ReadOnly;
import org.example.readonly.domain.repository.ReadOnlyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadOnlyService {

    private final ReadOnlyRepository readOnlyRepository;

    @Transactional(readOnly = true, value = "transactionManager")
    public List<ReadOnly> findAll() {
        boolean isTransaction = TransactionSynchronizationManager.isActualTransactionActive();
        log.info("is transaction = {}", isTransaction);
        return readOnlyRepository.findAll();
    }

    public List<ReadOnly> findAllNoTran() {
        boolean isTransaction = TransactionSynchronizationManager.isActualTransactionActive();
        log.info("is transaction = {}", isTransaction);
        return readOnlyRepository.findAll();
    }
}
