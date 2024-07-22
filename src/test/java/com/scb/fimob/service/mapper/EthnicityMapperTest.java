package com.scb.fimob.service.mapper;

import static com.scb.fimob.domain.EthnicityAsserts.*;
import static com.scb.fimob.domain.EthnicityTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EthnicityMapperTest {

    private EthnicityMapper ethnicityMapper;

    @BeforeEach
    void setUp() {
        ethnicityMapper = new EthnicityMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getEthnicitySample1();
        var actual = ethnicityMapper.toEntity(ethnicityMapper.toDto(expected));
        assertEthnicityAllPropertiesEquals(expected, actual);
    }
}
