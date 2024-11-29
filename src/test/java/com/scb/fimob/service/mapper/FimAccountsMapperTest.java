package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.FimAccountsAsserts.*;
import static com.scb.fimob.domain.FimAccountsTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FimAccountsMapperTest {

    private FimAccountsMapper fimAccountsMapper;

    @BeforeEach
    void setUp() {
        fimAccountsMapper = new FimAccountsMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFimAccountsSample1();
        var actual = fimAccountsMapper.toEntity(fimAccountsMapper.toDto(expected));
        assertFimAccountsAllPropertiesEquals(expected, actual);
    }
}
