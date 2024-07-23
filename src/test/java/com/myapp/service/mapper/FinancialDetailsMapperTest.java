package com.myapp.service.mapper;

import static com.myapp.domain.FinancialDetailsAsserts.*;
import static com.myapp.domain.FinancialDetailsTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinancialDetailsMapperTest {

    private FinancialDetailsMapper financialDetailsMapper;

    @BeforeEach
    void setUp() {
        financialDetailsMapper = new FinancialDetailsMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFinancialDetailsSample1();
        var actual = financialDetailsMapper.toEntity(financialDetailsMapper.toDto(expected));
        assertFinancialDetailsAllPropertiesEquals(expected, actual);
    }
}
