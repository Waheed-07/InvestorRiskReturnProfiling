package com.simplerishta.cms.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.simplerishta.cms.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProfileVerificationStatusDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfileVerificationStatusDTO.class);
        ProfileVerificationStatusDTO profileVerificationStatusDTO1 = new ProfileVerificationStatusDTO();
        profileVerificationStatusDTO1.setId(1L);
        ProfileVerificationStatusDTO profileVerificationStatusDTO2 = new ProfileVerificationStatusDTO();
        assertThat(profileVerificationStatusDTO1).isNotEqualTo(profileVerificationStatusDTO2);
        profileVerificationStatusDTO2.setId(profileVerificationStatusDTO1.getId());
        assertThat(profileVerificationStatusDTO1).isEqualTo(profileVerificationStatusDTO2);
        profileVerificationStatusDTO2.setId(2L);
        assertThat(profileVerificationStatusDTO1).isNotEqualTo(profileVerificationStatusDTO2);
        profileVerificationStatusDTO1.setId(null);
        assertThat(profileVerificationStatusDTO1).isNotEqualTo(profileVerificationStatusDTO2);
    }
}
