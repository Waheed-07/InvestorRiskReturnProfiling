package com.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.myapp.domain.Options} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OptionsDTO implements Serializable {

    private Long id;

    @NotNull
    private String optionText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OptionsDTO)) {
            return false;
        }

        OptionsDTO optionsDTO = (OptionsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, optionsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OptionsDTO{" +
            "id=" + getId() +
            ", optionText='" + getOptionText() + "'" +
            "}";
    }
}
