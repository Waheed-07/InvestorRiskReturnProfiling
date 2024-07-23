package com.myapp.service.mapper;

import static com.myapp.domain.PersonalDetailsAsserts.*;
import static com.myapp.domain.PersonalDetailsTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonalDetailsMapperTest {

    private PersonalDetailsMapper personalDetailsMapper;

    @BeforeEach
    void setUp() {
        personalDetailsMapper = new PersonalDetailsMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPersonalDetailsSample1();
        var actual = personalDetailsMapper.toEntity(personalDetailsMapper.toDto(expected));
        assertPersonalDetailsAllPropertiesEquals(expected, actual);
    }
}
