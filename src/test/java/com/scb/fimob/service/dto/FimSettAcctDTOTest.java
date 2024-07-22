package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimSettAcctDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimSettAcctDTO.class);
        FimSettAcctDTO fimSettAcctDTO1 = new FimSettAcctDTO();
        fimSettAcctDTO1.setId(1L);
        FimSettAcctDTO fimSettAcctDTO2 = new FimSettAcctDTO();
        assertThat(fimSettAcctDTO1).isNotEqualTo(fimSettAcctDTO2);
        fimSettAcctDTO2.setId(fimSettAcctDTO1.getId());
        assertThat(fimSettAcctDTO1).isEqualTo(fimSettAcctDTO2);
        fimSettAcctDTO2.setId(2L);
        assertThat(fimSettAcctDTO1).isNotEqualTo(fimSettAcctDTO2);
        fimSettAcctDTO1.setId(null);
        assertThat(fimSettAcctDTO1).isNotEqualTo(fimSettAcctDTO2);
    }
}
