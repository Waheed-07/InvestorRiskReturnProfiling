package com.simplerishta.cms.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.simplerishta.cms.domain.ProfileVerificationStatus} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProfileVerificationStatusDTO implements Serializable {

    private Long id;

    @Size(max = 200)
    private String attributeName;

    @Size(max = 100)
    private String status;

    private Instant verifiedAt;

    private String verifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Instant verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileVerificationStatusDTO)) {
            return false;
        }

        ProfileVerificationStatusDTO profileVerificationStatusDTO = (ProfileVerificationStatusDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, profileVerificationStatusDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileVerificationStatusDTO{" +
            "id=" + getId() +
            ", attributeName='" + getAttributeName() + "'" +
            ", status='" + getStatus() + "'" +
            ", verifiedAt='" + getVerifiedAt() + "'" +
            ", verifiedBy='" + getVerifiedBy() + "'" +
            "}";
    }
}
