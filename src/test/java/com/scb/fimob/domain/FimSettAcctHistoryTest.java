package com.scb.fimob.domain;

import static com.scb.fimob.domain.FimSettAcctHistoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimSettAcctHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimSettAcctHistory.class);
        FimSettAcctHistory fimSettAcctHistory1 = getFimSettAcctHistorySample1();
        FimSettAcctHistory fimSettAcctHistory2 = new FimSettAcctHistory();
        assertThat(fimSettAcctHistory1).isNotEqualTo(fimSettAcctHistory2);

        fimSettAcctHistory2.setId(fimSettAcctHistory1.getId());
        assertThat(fimSettAcctHistory1).isEqualTo(fimSettAcctHistory2);

        fimSettAcctHistory2 = getFimSettAcctHistorySample2();
        assertThat(fimSettAcctHistory1).isNotEqualTo(fimSettAcctHistory2);
    }
}
