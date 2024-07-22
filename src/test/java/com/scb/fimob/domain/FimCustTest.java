package com.scb.fimob.domain;

import static com.scb.fimob.domain.FimCustTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimCustTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimCust.class);
        FimCust fimCust1 = getFimCustSample1();
        FimCust fimCust2 = new FimCust();
        assertThat(fimCust1).isNotEqualTo(fimCust2);

        fimCust2.setId(fimCust1.getId());
        assertThat(fimCust1).isEqualTo(fimCust2);

        fimCust2 = getFimCustSample2();
        assertThat(fimCust1).isNotEqualTo(fimCust2);
    }
}
