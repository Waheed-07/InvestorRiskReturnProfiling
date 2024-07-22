package com.scb.fimob.domain;

import static com.scb.fimob.domain.FimAccountsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimAccountsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimAccounts.class);
        FimAccounts fimAccounts1 = getFimAccountsSample1();
        FimAccounts fimAccounts2 = new FimAccounts();
        assertThat(fimAccounts1).isNotEqualTo(fimAccounts2);

        fimAccounts2.setId(fimAccounts1.getId());
        assertThat(fimAccounts1).isEqualTo(fimAccounts2);

        fimAccounts2 = getFimAccountsSample2();
        assertThat(fimAccounts1).isNotEqualTo(fimAccounts2);
    }
}
