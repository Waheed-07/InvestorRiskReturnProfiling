package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.FimSettAcctAsserts.*;
import static com.scb.fimob.domain.FimSettAcctTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FimSettAcctMapperTest {

    private FimSettAcctMapper fimSettAcctMapper;

    @BeforeEach
    void setUp() {
        fimSettAcctMapper = new FimSettAcctMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFimSettAcctSample1();
        var actual = fimSettAcctMapper.toEntity(fimSettAcctMapper.toDto(expected));
        assertFimSettAcctAllPropertiesEquals(expected, actual);
    }
}
