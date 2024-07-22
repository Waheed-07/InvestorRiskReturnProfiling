package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.FimCustAsserts.*;
import static com.scb.fimob.domain.FimCustTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FimCustMapperTest {

    private FimCustMapper fimCustMapper;

    @BeforeEach
    void setUp() {
        fimCustMapper = new FimCustMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFimCustSample1();
        var actual = fimCustMapper.toEntity(fimCustMapper.toDto(expected));
        assertFimCustAllPropertiesEquals(expected, actual);
    }
}
