package com.scb.fimob.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FimSettAcctWqTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FimSettAcctWq getFimSettAcctWqSample1() {
        return new FimSettAcctWq()
            .id(1L)
            .settaccId("settaccId1")
            .accountId("accountId1")
            .settAcctNbr("settAcctNbr1")
            .settCcy("settCcy1")
            .settAcctStatus("settAcctStatus1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1")
            .recordStatus("recordStatus1")
            .uploadRemark("uploadRemark1");
    }

    public static FimSettAcctWq getFimSettAcctWqSample2() {
        return new FimSettAcctWq()
            .id(2L)
            .settaccId("settaccId2")
            .accountId("accountId2")
            .settAcctNbr("settAcctNbr2")
            .settCcy("settCcy2")
            .settAcctStatus("settAcctStatus2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2")
            .recordStatus("recordStatus2")
            .uploadRemark("uploadRemark2");
    }

    public static FimSettAcctWq getFimSettAcctWqRandomSampleGenerator() {
        return new FimSettAcctWq()
            .id(longCount.incrementAndGet())
            .settaccId(UUID.randomUUID().toString())
            .accountId(UUID.randomUUID().toString())
            .settAcctNbr(UUID.randomUUID().toString())
            .settCcy(UUID.randomUUID().toString())
            .settAcctStatus(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString())
            .recordStatus(UUID.randomUUID().toString())
            .uploadRemark(UUID.randomUUID().toString());
    }
}
