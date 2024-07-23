package com.myapp.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.myapp.domain.Profession} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProfessionDTO implements Serializable {

    private Long id;

    private String name;

    private String conceptUri;

    private String iscoGroup;

    @Lob
    private String description;

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

    public String getConceptUri() {
        return conceptUri;
    }

    public void setConceptUri(String conceptUri) {
        this.conceptUri = conceptUri;
    }

    public String getIscoGroup() {
        return iscoGroup;
    }

    public void setIscoGroup(String iscoGroup) {
        this.iscoGroup = iscoGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfessionDTO)) {
            return false;
        }

        ProfessionDTO professionDTO = (ProfessionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, professionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfessionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", conceptUri='" + getConceptUri() + "'" +
            ", iscoGroup='" + getIscoGroup() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
