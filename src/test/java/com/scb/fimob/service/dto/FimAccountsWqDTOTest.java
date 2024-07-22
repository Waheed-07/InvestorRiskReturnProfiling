package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimAccountsWqDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimAccountsWqDTO.class);
        FimAccountsWqDTO fimAccountsWqDTO1 = new FimAccountsWqDTO();
        fimAccountsWqDTO1.setId(1L);
        FimAccountsWqDTO fimAccountsWqDTO2 = new FimAccountsWqDTO();
        assertThat(fimAccountsWqDTO1).isNotEqualTo(fimAccountsWqDTO2);
        fimAccountsWqDTO2.setId(fimAccountsWqDTO1.getId());
        assertThat(fimAccountsWqDTO1).isEqualTo(fimAccountsWqDTO2);
        fimAccountsWqDTO2.setId(2L);
        assertThat(fimAccountsWqDTO1).isNotEqualTo(fimAccountsWqDTO2);
        fimAccountsWqDTO1.setId(null);
        assertThat(fimAccountsWqDTO1).isNotEqualTo(fimAccountsWqDTO2);
    }
}
