package com.scb.fimob.domain;

import static com.scb.fimob.domain.EthnicityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.scb.fimob.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EthnicityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ethnicity.class);
        Ethnicity ethnicity1 = getEthnicitySample1();
        Ethnicity ethnicity2 = new Ethnicity();
        assertThat(ethnicity1).isNotEqualTo(ethnicity2);

        ethnicity2.setId(ethnicity1.getId());
        assertThat(ethnicity1).isEqualTo(ethnicity2);

        ethnicity2 = getEthnicitySample2();
        assertThat(ethnicity1).isNotEqualTo(ethnicity2);
    }
}
