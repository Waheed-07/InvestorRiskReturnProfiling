package com.myapp.service.mapper;

import static com.myapp.domain.OptionsAsserts.*;
import static com.myapp.domain.OptionsTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OptionsMapperTest {

    private OptionsMapper optionsMapper;

    @BeforeEach
    void setUp() {
        optionsMapper = new OptionsMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getOptionsSample1();
        var actual = optionsMapper.toEntity(optionsMapper.toDto(expected));
        assertOptionsAllPropertiesEquals(expected, actual);
    }
}
