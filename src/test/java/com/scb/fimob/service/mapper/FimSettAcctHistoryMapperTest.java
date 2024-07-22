package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.FimSettAcctHistoryAsserts.*;
import static com.scb.fimob.domain.FimSettAcctHistoryTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FimSettAcctHistoryMapperTest {

    private FimSettAcctHistoryMapper fimSettAcctHistoryMapper;

    @BeforeEach
    void setUp() {
        fimSettAcctHistoryMapper = new FimSettAcctHistoryMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFimSettAcctHistorySample1();
        var actual = fimSettAcctHistoryMapper.toEntity(fimSettAcctHistoryMapper.toDto(expected));
        assertFimSettAcctHistoryAllPropertiesEquals(expected, actual);
    }
}
