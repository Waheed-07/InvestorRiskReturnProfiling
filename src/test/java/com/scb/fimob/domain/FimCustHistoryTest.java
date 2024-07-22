package com.scb.fimob.domain;

import static com.scb.fimob.domain.FimCustHistoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimCustHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimCustHistory.class);
        FimCustHistory fimCustHistory1 = getFimCustHistorySample1();
        FimCustHistory fimCustHistory2 = new FimCustHistory();
        assertThat(fimCustHistory1).isNotEqualTo(fimCustHistory2);

        fimCustHistory2.setId(fimCustHistory1.getId());
        assertThat(fimCustHistory1).isEqualTo(fimCustHistory2);

        fimCustHistory2 = getFimCustHistorySample2();
        assertThat(fimCustHistory1).isNotEqualTo(fimCustHistory2);
    }
}
