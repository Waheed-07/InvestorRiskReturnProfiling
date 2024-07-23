package com.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.myapp.domain.Questions} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionsDTO implements Serializable {

    private Long id;

    @NotNull
    private String questionText;

    @NotNull
    private String questionType;

    @NotNull
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionsDTO)) {
            return false;
        }

        QuestionsDTO questionsDTO = (QuestionsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionsDTO{" +
            "id=" + getId() +
            ", questionText='" + getQuestionText() + "'" +
            ", questionType='" + getQuestionType() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}
