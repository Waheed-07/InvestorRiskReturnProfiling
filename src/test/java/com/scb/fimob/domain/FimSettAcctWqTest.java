package com.scb.fimob.domain;

import static com.scb.fimob.domain.FimSettAcctWqTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimSettAcctWqTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimSettAcctWq.class);
        FimSettAcctWq fimSettAcctWq1 = getFimSettAcctWqSample1();
        FimSettAcctWq fimSettAcctWq2 = new FimSettAcctWq();
        assertThat(fimSettAcctWq1).isNotEqualTo(fimSettAcctWq2);

        fimSettAcctWq2.setId(fimSettAcctWq1.getId());
        assertThat(fimSettAcctWq1).isEqualTo(fimSettAcctWq2);

        fimSettAcctWq2 = getFimSettAcctWqSample2();
        assertThat(fimSettAcctWq1).isNotEqualTo(fimSettAcctWq2);
    }
}
