package com.myapp.domain;

import static com.myapp.domain.FinancialDetailsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FinancialDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancialDetails.class);
        FinancialDetails financialDetails1 = getFinancialDetailsSample1();
        FinancialDetails financialDetails2 = new FinancialDetails();
        assertThat(financialDetails1).isNotEqualTo(financialDetails2);

        financialDetails2.setId(financialDetails1.getId());
        assertThat(financialDetails1).isEqualTo(financialDetails2);

        financialDetails2 = getFinancialDetailsSample2();
        assertThat(financialDetails1).isNotEqualTo(financialDetails2);
    }
}
