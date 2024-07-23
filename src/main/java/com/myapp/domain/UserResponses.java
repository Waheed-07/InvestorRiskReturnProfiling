package com.myapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UserResponses.
 */
@Entity
@Table(name = "user_responses")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserResponses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "response_text", nullable = false)
    private String responseText;

    @NotNull
    @Column(name = "response_date", nullable = false)
    private Instant responseDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserResponses id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponseText() {
        return this.responseText;
    }

    public UserResponses responseText(String responseText) {
        this.setResponseText(responseText);
        return this;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Instant getResponseDate() {
        return this.responseDate;
    }

    public UserResponses responseDate(Instant responseDate) {
        this.setResponseDate(responseDate);
        return this;
    }

    public void setResponseDate(Instant responseDate) {
        this.responseDate = responseDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserResponses)) {
            return false;
        }
        return getId() != null && getId().equals(((UserResponses) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserResponses{" +
            "id=" + getId() +
            ", responseText='" + getResponseText() + "'" +
            ", responseDate='" + getResponseDate() + "'" +
            "}";
    }
}
