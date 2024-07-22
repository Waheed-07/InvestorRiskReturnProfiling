package com.scb.fimob.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class EthnicityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Ethnicity getEthnicitySample1() {
        return new Ethnicity().id(1L).name("name1").urduName("urduName1");
    }

    public static Ethnicity getEthnicitySample2() {
        return new Ethnicity().id(2L).name("name2").urduName("urduName2");
    }

    public static Ethnicity getEthnicityRandomSampleGenerator() {
        return new Ethnicity().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString()).urduName(UUID.randomUUID().toString());
    }
}
