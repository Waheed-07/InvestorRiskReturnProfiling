package com.scb.fimob.domain;

import static com.scb.fimob.domain.FimAccountsWqTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimAccountsWqTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimAccountsWq.class);
        FimAccountsWq fimAccountsWq1 = getFimAccountsWqSample1();
        FimAccountsWq fimAccountsWq2 = new FimAccountsWq();
        assertThat(fimAccountsWq1).isNotEqualTo(fimAccountsWq2);

        fimAccountsWq2.setId(fimAccountsWq1.getId());
        assertThat(fimAccountsWq1).isEqualTo(fimAccountsWq2);

        fimAccountsWq2 = getFimAccountsWqSample2();
        assertThat(fimAccountsWq1).isNotEqualTo(fimAccountsWq2);
    }
}
