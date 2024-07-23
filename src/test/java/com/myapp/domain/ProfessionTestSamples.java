package com.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ProfessionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Profession getProfessionSample1() {
        return new Profession().id(1L).name("name1").conceptUri("conceptUri1").iscoGroup("iscoGroup1");
    }

    public static Profession getProfessionSample2() {
        return new Profession().id(2L).name("name2").conceptUri("conceptUri2").iscoGroup("iscoGroup2");
    }

    public static Profession getProfessionRandomSampleGenerator() {
        return new Profession()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .conceptUri(UUID.randomUUID().toString())
            .iscoGroup(UUID.randomUUID().toString());
    }
}
