package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.FimCustHistoryAsserts.*;
import static com.scb.fimob.domain.FimCustHistoryTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FimCustHistoryMapperTest {

    private FimCustHistoryMapper fimCustHistoryMapper;

    @BeforeEach
    void setUp() {
        fimCustHistoryMapper = new FimCustHistoryMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFimCustHistorySample1();
        var actual = fimCustHistoryMapper.toEntity(fimCustHistoryMapper.toDto(expected));
        assertFimCustHistoryAllPropertiesEquals(expected, actual);
    }
}
