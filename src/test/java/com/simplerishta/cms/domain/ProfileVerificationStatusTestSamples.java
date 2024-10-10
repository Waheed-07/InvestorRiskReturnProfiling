package com.simplerishta.cms.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileVerificationStatusTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ProfileVerificationStatus getProfileVerificationStatusSample1() {
        return new ProfileVerificationStatus().id(1L).attributeName("attributeName1").status("status1").verifiedBy("verifiedBy1");
    }

    public static ProfileVerificationStatus getProfileVerificationStatusSample2() {
        return new ProfileVerificationStatus().id(2L).attributeName("attributeName2").status("status2").verifiedBy("verifiedBy2");
    }

    public static ProfileVerificationStatus getProfileVerificationStatusRandomSampleGenerator() {
        return new ProfileVerificationStatus()
            .id(longCount.incrementAndGet())
            .attributeName(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .verifiedBy(UUID.randomUUID().toString());
    }
}
