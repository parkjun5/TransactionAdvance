package org.example.apply;

import org.example.application.NonReadOnlyService;
import org.example.domain.NonReadOnly;
import org.example.domain.ReadOnly;
import org.example.domain.repository.NonReadOnlyRepository;
import org.example.domain.repository.ReadOnlyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class InternalCallV3ReadOnly {

    @Autowired
    NonReadOnlyRepository nonReadOnlyRepository;
    @Autowired
    ReadOnlyRepository readOnlyRepository;
    @Autowired
    NonReadOnlyService nonReadOnlyService;


    @BeforeEach
    void initDB() {
        List<NonReadOnly> nonReadOnlyList = IntStream.range(0, 3).mapToObj(this::createNonReadOnly).collect(Collectors.toList());
        nonReadOnlyRepository.saveAll(nonReadOnlyList);

        List<ReadOnly> readOnlyList = IntStream.range(0, 3).mapToObj(this::createReadOnly).collect(Collectors.toList());
        readOnlyRepository.saveAll(readOnlyList);
    }

    @Test
    @DisplayName("더티체킹으로 리드온리 설정이 되어있는지 확인한다")
    void internalReadOnlyTest() {
        nonReadOnlyService.internalTest();
    }

    private NonReadOnly createNonReadOnly(int index) {
        return new NonReadOnly("변경 가능_" + index);
    }

    private ReadOnly createReadOnly(int index) {
        return new ReadOnly("읽기 전용_" + index);
    }

}
