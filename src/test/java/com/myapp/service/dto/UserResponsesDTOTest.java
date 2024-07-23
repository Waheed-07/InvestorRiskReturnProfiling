package com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserResponsesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserResponsesDTO.class);
        UserResponsesDTO userResponsesDTO1 = new UserResponsesDTO();
        userResponsesDTO1.setId(1L);
        UserResponsesDTO userResponsesDTO2 = new UserResponsesDTO();
        assertThat(userResponsesDTO1).isNotEqualTo(userResponsesDTO2);
        userResponsesDTO2.setId(userResponsesDTO1.getId());
        assertThat(userResponsesDTO1).isEqualTo(userResponsesDTO2);
        userResponsesDTO2.setId(2L);
        assertThat(userResponsesDTO1).isNotEqualTo(userResponsesDTO2);
        userResponsesDTO1.setId(null);
        assertThat(userResponsesDTO1).isNotEqualTo(userResponsesDTO2);
    }
}
