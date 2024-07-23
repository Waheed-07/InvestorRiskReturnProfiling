package com.myapp.domain;

import static com.myapp.domain.PersonalDetailsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PersonalDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalDetails.class);
        PersonalDetails personalDetails1 = getPersonalDetailsSample1();
        PersonalDetails personalDetails2 = new PersonalDetails();
        assertThat(personalDetails1).isNotEqualTo(personalDetails2);

        personalDetails2.setId(personalDetails1.getId());
        assertThat(personalDetails1).isEqualTo(personalDetails2);

        personalDetails2 = getPersonalDetailsSample2();
        assertThat(personalDetails1).isNotEqualTo(personalDetails2);
    }
}
