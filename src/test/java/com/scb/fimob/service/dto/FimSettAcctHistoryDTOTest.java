package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimSettAcctHistoryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimSettAcctHistoryDTO.class);
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO1 = new FimSettAcctHistoryDTO();
        fimSettAcctHistoryDTO1.setId(1L);
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO2 = new FimSettAcctHistoryDTO();
        assertThat(fimSettAcctHistoryDTO1).isNotEqualTo(fimSettAcctHistoryDTO2);
        fimSettAcctHistoryDTO2.setId(fimSettAcctHistoryDTO1.getId());
        assertThat(fimSettAcctHistoryDTO1).isEqualTo(fimSettAcctHistoryDTO2);
        fimSettAcctHistoryDTO2.setId(2L);
        assertThat(fimSettAcctHistoryDTO1).isNotEqualTo(fimSettAcctHistoryDTO2);
        fimSettAcctHistoryDTO1.setId(null);
        assertThat(fimSettAcctHistoryDTO1).isNotEqualTo(fimSettAcctHistoryDTO2);
    }
}
