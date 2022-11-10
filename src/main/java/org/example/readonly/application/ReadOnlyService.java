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
@Transactional(readOnly = true, value = "transactionManager")
@RequiredArgsConstructor
public class ReadOnlyService {

    private final ReadOnlyRepository readOnlyRepository;

    public List<ReadOnly> findAll() {
        TransactionSynchronizationManager.setCurrentTransactionReadOnly(true);
        boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        log.info("ReadOnlyService method read only = {}", readOnly);
        return readOnlyRepository.findAll();
    }
}
