package com.scb.fimob.domain;

import static com.scb.fimob.domain.FimCustWqTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimCustWqTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimCustWq.class);
        FimCustWq fimCustWq1 = getFimCustWqSample1();
        FimCustWq fimCustWq2 = new FimCustWq();
        assertThat(fimCustWq1).isNotEqualTo(fimCustWq2);

        fimCustWq2.setId(fimCustWq1.getId());
        assertThat(fimCustWq1).isEqualTo(fimCustWq2);

        fimCustWq2 = getFimCustWqSample2();
        assertThat(fimCustWq1).isNotEqualTo(fimCustWq2);
    }
}
