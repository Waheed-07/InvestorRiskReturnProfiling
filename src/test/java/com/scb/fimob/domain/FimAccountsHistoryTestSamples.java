package com.scb.fimob.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FimAccountsHistoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FimAccountsHistory getFimAccountsHistorySample1() {
        return new FimAccountsHistory()
            .id(1L)
            .accountId("accountId1")
            .custId("custId1")
            .relnId("relnId1")
            .relnType("relnType1")
            .operInst("operInst1")
            .isAcctNbr("isAcctNbr1")
            .bndAcctNbr("bndAcctNbr1")
            .closingId("closingId1")
            .subSegment("subSegment1")
            .branchCode("branchCode1")
            .acctStatus("acctStatus1")
            .ctryCode("ctryCode1")
            .acctOwners("acctOwners1")
            .remarks("remarks1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1")
            .recordStatus("recordStatus1");
    }

    public static FimAccountsHistory getFimAccountsHistorySample2() {
        return new FimAccountsHistory()
            .id(2L)
            .accountId("accountId2")
            .custId("custId2")
            .relnId("relnId2")
            .relnType("relnType2")
            .operInst("operInst2")
            .isAcctNbr("isAcctNbr2")
            .bndAcctNbr("bndAcctNbr2")
            .closingId("closingId2")
            .subSegment("subSegment2")
            .branchCode("branchCode2")
            .acctStatus("acctStatus2")
            .ctryCode("ctryCode2")
            .acctOwners("acctOwners2")
            .remarks("remarks2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2")
            .recordStatus("recordStatus2");
    }

    public static FimAccountsHistory getFimAccountsHistoryRandomSampleGenerator() {
        return new FimAccountsHistory()
            .id(longCount.incrementAndGet())
            .accountId(UUID.randomUUID().toString())
            .custId(UUID.randomUUID().toString())
            .relnId(UUID.randomUUID().toString())
            .relnType(UUID.randomUUID().toString())
            .operInst(UUID.randomUUID().toString())
            .isAcctNbr(UUID.randomUUID().toString())
            .bndAcctNbr(UUID.randomUUID().toString())
            .closingId(UUID.randomUUID().toString())
            .subSegment(UUID.randomUUID().toString())
            .branchCode(UUID.randomUUID().toString())
            .acctStatus(UUID.randomUUID().toString())
            .ctryCode(UUID.randomUUID().toString())
            .acctOwners(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString())
            .recordStatus(UUID.randomUUID().toString());
    }
}
