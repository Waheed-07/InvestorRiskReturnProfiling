package com.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FinancialDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancialDetailsDTO.class);
        FinancialDetailsDTO financialDetailsDTO1 = new FinancialDetailsDTO();
        financialDetailsDTO1.setId(1L);
        FinancialDetailsDTO financialDetailsDTO2 = new FinancialDetailsDTO();
        assertThat(financialDetailsDTO1).isNotEqualTo(financialDetailsDTO2);
        financialDetailsDTO2.setId(financialDetailsDTO1.getId());
        assertThat(financialDetailsDTO1).isEqualTo(financialDetailsDTO2);
        financialDetailsDTO2.setId(2L);
        assertThat(financialDetailsDTO1).isNotEqualTo(financialDetailsDTO2);
        financialDetailsDTO1.setId(null);
        assertThat(financialDetailsDTO1).isNotEqualTo(financialDetailsDTO2);
    }
}
