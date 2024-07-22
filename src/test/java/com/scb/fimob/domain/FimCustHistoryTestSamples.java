package com.scb.fimob.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FimCustHistoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FimCustHistory getFimCustHistorySample1() {
        return new FimCustHistory()
            .id(1L)
            .custId("custId1")
            .clientId("clientId1")
            .idType("idType1")
            .ctryCode("ctryCode1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1")
            .recordStatus("recordStatus1");
    }

    public static FimCustHistory getFimCustHistorySample2() {
        return new FimCustHistory()
            .id(2L)
            .custId("custId2")
            .clientId("clientId2")
            .idType("idType2")
            .ctryCode("ctryCode2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2")
            .recordStatus("recordStatus2");
    }

    public static FimCustHistory getFimCustHistoryRandomSampleGenerator() {
        return new FimCustHistory()
            .id(longCount.incrementAndGet())
            .custId(UUID.randomUUID().toString())
            .clientId(UUID.randomUUID().toString())
            .idType(UUID.randomUUID().toString())
            .ctryCode(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString())
            .recordStatus(UUID.randomUUID().toString());
    }
}
