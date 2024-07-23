package com.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FinancialDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FinancialDetails getFinancialDetailsSample1() {
        return new FinancialDetails().id(1L).investmentExperience("investmentExperience1").sourceOfFunds("sourceOfFunds1");
    }

    public static FinancialDetails getFinancialDetailsSample2() {
        return new FinancialDetails().id(2L).investmentExperience("investmentExperience2").sourceOfFunds("sourceOfFunds2");
    }

    public static FinancialDetails getFinancialDetailsRandomSampleGenerator() {
        return new FinancialDetails()
            .id(longCount.incrementAndGet())
            .investmentExperience(UUID.randomUUID().toString())
            .sourceOfFunds(UUID.randomUUID().toString());
    }
}
