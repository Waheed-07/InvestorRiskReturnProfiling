package com.myapp.domain;

import static com.myapp.domain.UserResponsesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserResponsesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserResponses.class);
        UserResponses userResponses1 = getUserResponsesSample1();
        UserResponses userResponses2 = new UserResponses();
        assertThat(userResponses1).isNotEqualTo(userResponses2);

        userResponses2.setId(userResponses1.getId());
        assertThat(userResponses1).isEqualTo(userResponses2);

        userResponses2 = getUserResponsesSample2();
        assertThat(userResponses1).isNotEqualTo(userResponses2);
    }
}
