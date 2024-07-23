package com.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.myapp.domain.UserResponses} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserResponsesDTO implements Serializable {

    private Long id;

    @NotNull
    private String responseText;

    @NotNull
    private Instant responseDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Instant getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Instant responseDate) {
        this.responseDate = responseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserResponsesDTO)) {
            return false;
        }

        UserResponsesDTO userResponsesDTO = (UserResponsesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userResponsesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserResponsesDTO{" +
            "id=" + getId() +
            ", responseText='" + getResponseText() + "'" +
            ", responseDate='" + getResponseDate() + "'" +
            "}";
    }
}
