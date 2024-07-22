package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.FimCustWqAsserts.*;
import static com.scb.fimob.domain.FimCustWqTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FimCustWqMapperTest {

    private FimCustWqMapper fimCustWqMapper;

    @BeforeEach
    void setUp() {
        fimCustWqMapper = new FimCustWqMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFimCustWqSample1();
        var actual = fimCustWqMapper.toEntity(fimCustWqMapper.toDto(expected));
        assertFimCustWqAllPropertiesEquals(expected, actual);
    }
}
