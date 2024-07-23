package com.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class QuestionsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Questions getQuestionsSample1() {
        return new Questions().id(1L).questionText("questionText1").questionType("questionType1");
    }

    public static Questions getQuestionsSample2() {
        return new Questions().id(2L).questionText("questionText2").questionType("questionType2");
    }

    public static Questions getQuestionsRandomSampleGenerator() {
        return new Questions()
            .id(longCount.incrementAndGet())
            .questionText(UUID.randomUUID().toString())
            .questionType(UUID.randomUUID().toString());
    }
}
