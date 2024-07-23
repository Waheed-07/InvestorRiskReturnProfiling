package com.myapp.service.mapper;

import static com.myapp.domain.ProfessionAsserts.*;
import static com.myapp.domain.ProfessionTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfessionMapperTest {

    private ProfessionMapper professionMapper;

    @BeforeEach
    void setUp() {
        professionMapper = new ProfessionMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getProfessionSample1();
        var actual = professionMapper.toEntity(professionMapper.toDto(expected));
        assertProfessionAllPropertiesEquals(expected, actual);
    }
}
