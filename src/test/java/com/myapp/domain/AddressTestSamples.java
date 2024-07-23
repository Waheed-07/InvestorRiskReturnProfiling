package com.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AddressTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Address getAddressSample1() {
        return new Address()
            .id(1L)
            .addressLineOne("addressLineOne1")
            .addressLineTwo("addressLineTwo1")
            .postalCode("postalCode1")
            .city("city1")
            .region("region1");
    }

    public static Address getAddressSample2() {
        return new Address()
            .id(2L)
            .addressLineOne("addressLineOne2")
            .addressLineTwo("addressLineTwo2")
            .postalCode("postalCode2")
            .city("city2")
            .region("region2");
    }

    public static Address getAddressRandomSampleGenerator() {
        return new Address()
            .id(longCount.incrementAndGet())
            .addressLineOne(UUID.randomUUID().toString())
            .addressLineTwo(UUID.randomUUID().toString())
            .postalCode(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .region(UUID.randomUUID().toString());
    }
}
