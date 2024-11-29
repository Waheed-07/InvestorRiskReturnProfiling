package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimAccountsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimAccountsDTO.class);
        FimAccountsDTO fimAccountsDTO1 = new FimAccountsDTO();
        fimAccountsDTO1.setId(1L);
        FimAccountsDTO fimAccountsDTO2 = new FimAccountsDTO();
        assertThat(fimAccountsDTO1).isNotEqualTo(fimAccountsDTO2);
        fimAccountsDTO2.setId(fimAccountsDTO1.getId());
        assertThat(fimAccountsDTO1).isEqualTo(fimAccountsDTO2);
        fimAccountsDTO2.setId(2L);
        assertThat(fimAccountsDTO1).isNotEqualTo(fimAccountsDTO2);
        fimAccountsDTO1.setId(null);
        assertThat(fimAccountsDTO1).isNotEqualTo(fimAccountsDTO2);
    }
}
