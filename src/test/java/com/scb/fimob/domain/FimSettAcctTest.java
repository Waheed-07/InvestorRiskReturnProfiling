package com.scb.fimob.domain;

import static com.scb.fimob.domain.FimSettAcctTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FimSettAcctTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FimSettAcct.class);
        FimSettAcct fimSettAcct1 = getFimSettAcctSample1();
        FimSettAcct fimSettAcct2 = new FimSettAcct();
        assertThat(fimSettAcct1).isNotEqualTo(fimSettAcct2);

        fimSettAcct2.setId(fimSettAcct1.getId());
        assertThat(fimSettAcct1).isEqualTo(fimSettAcct2);

        fimSettAcct2 = getFimSettAcctSample2();
        assertThat(fimSettAcct1).isNotEqualTo(fimSettAcct2);
    }
}
