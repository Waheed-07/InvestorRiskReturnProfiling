package com.scb.fimob.domain;

import static com.scb.fimob.domain.FimAccountsHistoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimAccountsHistoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimAccountsHistory.class);
        FimAccountsHistory fimAccountsHistory1 = getFimAccountsHistorySample1();
        FimAccountsHistory fimAccountsHistory2 = new FimAccountsHistory();
        assertThat(fimAccountsHistory1).isNotEqualTo(fimAccountsHistory2);

        fimAccountsHistory2.setId(fimAccountsHistory1.getId());
        assertThat(fimAccountsHistory1).isEqualTo(fimAccountsHistory2);

        fimAccountsHistory2 = getFimAccountsHistorySample2();
        assertThat(fimAccountsHistory1).isNotEqualTo(fimAccountsHistory2);
    }
}
