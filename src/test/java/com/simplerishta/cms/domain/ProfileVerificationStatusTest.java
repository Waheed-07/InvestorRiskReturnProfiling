package com.simplerishta.cms.domain;

import static com.simplerishta.cms.domain.ProfileVerificationStatusTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.simplerishta.cms.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProfileVerificationStatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfileVerificationStatus.class);
        ProfileVerificationStatus profileVerificationStatus1 = getProfileVerificationStatusSample1();
        ProfileVerificationStatus profileVerificationStatus2 = new ProfileVerificationStatus();
        assertThat(profileVerificationStatus1).isNotEqualTo(profileVerificationStatus2);

        profileVerificationStatus2.setId(profileVerificationStatus1.getId());
        assertThat(profileVerificationStatus1).isEqualTo(profileVerificationStatus2);

        profileVerificationStatus2 = getProfileVerificationStatusSample2();
        assertThat(profileVerificationStatus1).isNotEqualTo(profileVerificationStatus2);
    }
}
