package com.myapp.service.mapper;

import static com.myapp.domain.QuestionsAsserts.*;
import static com.myapp.domain.QuestionsTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionsMapperTest {

    private QuestionsMapper questionsMapper;

    @BeforeEach
    void setUp() {
        questionsMapper = new QuestionsMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getQuestionsSample1();
        var actual = questionsMapper.toEntity(questionsMapper.toDto(expected));
        assertQuestionsAllPropertiesEquals(expected, actual);
    }
}
