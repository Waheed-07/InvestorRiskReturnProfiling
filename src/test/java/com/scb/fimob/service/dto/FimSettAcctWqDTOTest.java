package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimSettAcctWqDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimSettAcctWqDTO.class);
        FimSettAcctWqDTO fimSettAcctWqDTO1 = new FimSettAcctWqDTO();
        fimSettAcctWqDTO1.setId(1L);
        FimSettAcctWqDTO fimSettAcctWqDTO2 = new FimSettAcctWqDTO();
        assertThat(fimSettAcctWqDTO1).isNotEqualTo(fimSettAcctWqDTO2);
        fimSettAcctWqDTO2.setId(fimSettAcctWqDTO1.getId());
        assertThat(fimSettAcctWqDTO1).isEqualTo(fimSettAcctWqDTO2);
        fimSettAcctWqDTO2.setId(2L);
        assertThat(fimSettAcctWqDTO1).isNotEqualTo(fimSettAcctWqDTO2);
        fimSettAcctWqDTO1.setId(null);
        assertThat(fimSettAcctWqDTO1).isNotEqualTo(fimSettAcctWqDTO2);
    }
}
