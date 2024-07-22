package com.scb.fimob.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimCustHistoryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimCustHistoryDTO.class);
        FimCustHistoryDTO fimCustHistoryDTO1 = new FimCustHistoryDTO();
        fimCustHistoryDTO1.setId(1L);
        FimCustHistoryDTO fimCustHistoryDTO2 = new FimCustHistoryDTO();
        assertThat(fimCustHistoryDTO1).isNotEqualTo(fimCustHistoryDTO2);
        fimCustHistoryDTO2.setId(fimCustHistoryDTO1.getId());
        assertThat(fimCustHistoryDTO1).isEqualTo(fimCustHistoryDTO2);
        fimCustHistoryDTO2.setId(2L);
        assertThat(fimCustHistoryDTO1).isNotEqualTo(fimCustHistoryDTO2);
        fimCustHistoryDTO1.setId(null);
        assertThat(fimCustHistoryDTO1).isNotEqualTo(fimCustHistoryDTO2);
    }
}
