package com.myapp.service.mapper;

import static com.myapp.domain.UserResponsesAsserts.*;
import static com.myapp.domain.UserResponsesTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserResponsesMapperTest {

    private UserResponsesMapper userResponsesMapper;

    @BeforeEach
    void setUp() {
        userResponsesMapper = new UserResponsesMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getUserResponsesSample1();
        var actual = userResponsesMapper.toEntity(userResponsesMapper.toDto(expected));
        assertUserResponsesAllPropertiesEquals(expected, actual);
    }
}
