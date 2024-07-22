package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimCustWqDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimCustWqDTO.class);
        FimCustWqDTO fimCustWqDTO1 = new FimCustWqDTO();
        fimCustWqDTO1.setId(1L);
        FimCustWqDTO fimCustWqDTO2 = new FimCustWqDTO();
        assertThat(fimCustWqDTO1).isNotEqualTo(fimCustWqDTO2);
        fimCustWqDTO2.setId(fimCustWqDTO1.getId());
        assertThat(fimCustWqDTO1).isEqualTo(fimCustWqDTO2);
        fimCustWqDTO2.setId(2L);
        assertThat(fimCustWqDTO1).isNotEqualTo(fimCustWqDTO2);
        fimCustWqDTO1.setId(null);
        assertThat(fimCustWqDTO1).isNotEqualTo(fimCustWqDTO2);
    }
}
