package com.simplerishta.cms.service.mapper;

import static com.simplerishta.cms.domain.ProfileVerificationStatusAsserts.*;
import static com.simplerishta.cms.domain.ProfileVerificationStatusTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfileVerificationStatusMapperTest {

    private ProfileVerificationStatusMapper profileVerificationStatusMapper;

    @BeforeEach
    void setUp() {
        profileVerificationStatusMapper = new ProfileVerificationStatusMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getProfileVerificationStatusSample1();
        var actual = profileVerificationStatusMapper.toEntity(profileVerificationStatusMapper.toDto(expected));
        assertProfileVerificationStatusAllPropertiesEquals(expected, actual);
    }
}
