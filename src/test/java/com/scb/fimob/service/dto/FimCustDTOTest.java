package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimCustDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimCustDTO.class);
        FimCustDTO fimCustDTO1 = new FimCustDTO();
        fimCustDTO1.setId(1L);
        FimCustDTO fimCustDTO2 = new FimCustDTO();
        assertThat(fimCustDTO1).isNotEqualTo(fimCustDTO2);
        fimCustDTO2.setId(fimCustDTO1.getId());
        assertThat(fimCustDTO1).isEqualTo(fimCustDTO2);
        fimCustDTO2.setId(2L);
        assertThat(fimCustDTO1).isNotEqualTo(fimCustDTO2);
        fimCustDTO1.setId(null);
        assertThat(fimCustDTO1).isNotEqualTo(fimCustDTO2);
    }
}
