package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimAccountsHistoryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimAccountsHistoryDTO.class);
        FimAccountsHistoryDTO fimAccountsHistoryDTO1 = new FimAccountsHistoryDTO();
        fimAccountsHistoryDTO1.setId(1L);
        FimAccountsHistoryDTO fimAccountsHistoryDTO2 = new FimAccountsHistoryDTO();
        assertThat(fimAccountsHistoryDTO1).isNotEqualTo(fimAccountsHistoryDTO2);
        fimAccountsHistoryDTO2.setId(fimAccountsHistoryDTO1.getId());
        assertThat(fimAccountsHistoryDTO1).isEqualTo(fimAccountsHistoryDTO2);
        fimAccountsHistoryDTO2.setId(2L);
        assertThat(fimAccountsHistoryDTO1).isNotEqualTo(fimAccountsHistoryDTO2);
        fimAccountsHistoryDTO1.setId(null);
        assertThat(fimAccountsHistoryDTO1).isNotEqualTo(fimAccountsHistoryDTO2);
    }
}
