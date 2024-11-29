package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.FimAccountsHistoryAsserts.*;
import static com.scb.fimob.domain.FimAccountsHistoryTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FimAccountsHistoryMapperTest {

    private FimAccountsHistoryMapper fimAccountsHistoryMapper;

    @BeforeEach
    void setUp() {
        fimAccountsHistoryMapper = new FimAccountsHistoryMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFimAccountsHistorySample1();
        var actual = fimAccountsHistoryMapper.toEntity(fimAccountsHistoryMapper.toDto(expected));
        assertFimAccountsHistoryAllPropertiesEquals(expected, actual);
    }
}
