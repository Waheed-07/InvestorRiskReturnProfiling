package com.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UserResponsesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static UserResponses getUserResponsesSample1() {
        return new UserResponses().id(1L).responseText("responseText1");
    }

    public static UserResponses getUserResponsesSample2() {
        return new UserResponses().id(2L).responseText("responseText2");
    }

    public static UserResponses getUserResponsesRandomSampleGenerator() {
        return new UserResponses().id(longCount.incrementAndGet()).responseText(UUID.randomUUID().toString());
    }
}
