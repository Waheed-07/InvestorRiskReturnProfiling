package com.myapp.domain;

import static com.myapp.domain.OptionsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OptionsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Options.class);
        Options options1 = getOptionsSample1();
        Options options2 = new Options();
        assertThat(options1).isNotEqualTo(options2);

        options2.setId(options1.getId());
        assertThat(options1).isEqualTo(options2);

        options2 = getOptionsSample2();
        assertThat(options1).isNotEqualTo(options2);
    }
}
