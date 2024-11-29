package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EthnicityDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EthnicityDTO.class);
        EthnicityDTO ethnicityDTO1 = new EthnicityDTO();
        ethnicityDTO1.setId(1L);
        EthnicityDTO ethnicityDTO2 = new EthnicityDTO();
        assertThat(ethnicityDTO1).isNotEqualTo(ethnicityDTO2);
        ethnicityDTO2.setId(ethnicityDTO1.getId());
        assertThat(ethnicityDTO1).isEqualTo(ethnicityDTO2);
        ethnicityDTO2.setId(2L);
        assertThat(ethnicityDTO1).isNotEqualTo(ethnicityDTO2);
        ethnicityDTO1.setId(null);
        assertThat(ethnicityDTO1).isNotEqualTo(ethnicityDTO2);
    }
}
