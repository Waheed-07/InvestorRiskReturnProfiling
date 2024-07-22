package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.FimAccountsWqAsserts.*;
import static com.scb.fimob.domain.FimAccountsWqTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FimAccountsWqMapperTest {

    private FimAccountsWqMapper fimAccountsWqMapper;

    @BeforeEach
    void setUp() {
        fimAccountsWqMapper = new FimAccountsWqMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFimAccountsWqSample1();
        var actual = fimAccountsWqMapper.toEntity(fimAccountsWqMapper.toDto(expected));
        assertFimAccountsWqAllPropertiesEquals(expected, actual);
    }
}
