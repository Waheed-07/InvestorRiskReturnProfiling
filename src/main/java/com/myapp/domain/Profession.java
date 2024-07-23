package com.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Profession.
 */
@Entity
@Table(name = "profession")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Profession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "concept_uri")
    private String conceptUri;

    @Column(name = "isco_group")
    private String iscoGroup;

    @Lob
    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Profession id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Profession name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConceptUri() {
        return this.conceptUri;
    }

    public Profession conceptUri(String conceptUri) {
        this.setConceptUri(conceptUri);
        return this;
    }

    public void setConceptUri(String conceptUri) {
        this.conceptUri = conceptUri;
    }

    public String getIscoGroup() {
        return this.iscoGroup;
    }

    public Profession iscoGroup(String iscoGroup) {
        this.setIscoGroup(iscoGroup);
        return this;
    }

    public void setIscoGroup(String iscoGroup) {
        this.iscoGroup = iscoGroup;
    }

    public String getDescription() {
        return this.description;
    }

    public Profession description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profession)) {
            return false;
        }
        return getId() != null && getId().equals(((Profession) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Profession{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", conceptUri='" + getConceptUri() + "'" +
            ", iscoGroup='" + getIscoGroup() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
