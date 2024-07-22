package com.scb.fimob.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FimSettAcctHistoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FimSettAcctHistory getFimSettAcctHistorySample1() {
        return new FimSettAcctHistory()
            .id(1L)
            .settaccId("settaccId1")
            .accountId("accountId1")
            .settAcctNbr("settAcctNbr1")
            .settCcy("settCcy1")
            .settAcctStatus("settAcctStatus1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1")
            .recordStatus("recordStatus1");
    }

    public static FimSettAcctHistory getFimSettAcctHistorySample2() {
        return new FimSettAcctHistory()
            .id(2L)
            .settaccId("settaccId2")
            .accountId("accountId2")
            .settAcctNbr("settAcctNbr2")
            .settCcy("settCcy2")
            .settAcctStatus("settAcctStatus2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2")
            .recordStatus("recordStatus2");
    }

    public static FimSettAcctHistory getFimSettAcctHistoryRandomSampleGenerator() {
        return new FimSettAcctHistory()
            .id(longCount.incrementAndGet())
            .settaccId(UUID.randomUUID().toString())
            .accountId(UUID.randomUUID().toString())
            .settAcctNbr(UUID.randomUUID().toString())
            .settCcy(UUID.randomUUID().toString())
            .settAcctStatus(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString())
            .recordStatus(UUID.randomUUID().toString());
    }
}
