package com.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OptionsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Options getOptionsSample1() {
        return new Options().id(1L).optionText("optionText1");
    }

    public static Options getOptionsSample2() {
        return new Options().id(2L).optionText("optionText2");
    }

    public static Options getOptionsRandomSampleGenerator() {
        return new Options().id(longCount.incrementAndGet()).optionText(UUID.randomUUID().toString());
    }
}
