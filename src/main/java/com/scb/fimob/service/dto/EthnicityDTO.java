package com.scb.fimob.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.scb.fimob.domain.Ethnicity} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EthnicityDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String urduName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrduName() {
        return urduName;
    }

    public void setUrduName(String urduName) {
        this.urduName = urduName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EthnicityDTO)) {
            return false;
        }

        EthnicityDTO ethnicityDTO = (EthnicityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ethnicityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EthnicityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", urduName='" + getUrduName() + "'" +
            "}";
    }
}
