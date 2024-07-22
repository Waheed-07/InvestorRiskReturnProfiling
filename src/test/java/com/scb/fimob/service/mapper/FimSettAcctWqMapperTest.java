package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.FimSettAcctWqAsserts.*;
import static com.scb.fimob.domain.FimSettAcctWqTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FimSettAcctWqMapperTest {

    private FimSettAcctWqMapper fimSettAcctWqMapper;

    @BeforeEach
    void setUp() {
        fimSettAcctWqMapper = new FimSettAcctWqMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFimSettAcctWqSample1();
        var actual = fimSettAcctWqMapper.toEntity(fimSettAcctWqMapper.toDto(expected));
        assertFimSettAcctWqAllPropertiesEquals(expected, actual);
    }
}
