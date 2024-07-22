package com.scb.fimob.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FimCust.
 */
@Entity
@Table(name = "fim_cust")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FimCust implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "cust_id")
    private String custId;

    @NotNull
    @Size(max = 30)
    @Column(name = "client_id", length = 30, nullable = false)
    private String clientId;

    @NotNull
    @Size(max = 10)
    @Column(name = "id_type", length = 10, nullable = false)
    private String idType;

    @NotNull
    @Size(max = 3)
    @Column(name = "ctry_code", length = 3, nullable = false)
    private String ctryCode;

    @NotNull
    @Size(max = 8)
    @Column(name = "created_by", length = 8, nullable = false)
    private String createdBy;

    @Column(name = "created_ts")
    private Instant createdTs;

    @Size(max = 8)
    @Column(name = "updated_by", length = 8)
    private String updatedBy;

    @Column(name = "updated_ts")
    private Instant updatedTs;

    @Column(name = "record_status")
    private String recordStatus;

    @Column(name = "upload_remark")
    private String uploadRemark;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FimCust id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustId() {
        return this.custId;
    }

    public FimCust custId(String custId) {
        this.setCustId(custId);
        return this;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public FimCust clientId(String clientId) {
        this.setClientId(clientId);
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIdType() {
        return this.idType;
    }

    public FimCust idType(String idType) {
        this.setIdType(idType);
        return this;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCtryCode() {
        return this.ctryCode;
    }

    public FimCust ctryCode(String ctryCode) {
        this.setCtryCode(ctryCode);
        return this;
    }

    public void setCtryCode(String ctryCode) {
        this.ctryCode = ctryCode;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public FimCust createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedTs() {
        return this.createdTs;
    }

    public FimCust createdTs(Instant createdTs) {
        this.setCreatedTs(createdTs);
        return this;
    }

    public void setCreatedTs(Instant createdTs) {
        this.createdTs = createdTs;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public FimCust updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedTs() {
        return this.updatedTs;
    }

    public FimCust updatedTs(Instant updatedTs) {
        this.setUpdatedTs(updatedTs);
        return this;
    }

    public void setUpdatedTs(Instant updatedTs) {
        this.updatedTs = updatedTs;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public FimCust recordStatus(String recordStatus) {
        this.setRecordStatus(recordStatus);
        return this;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getUploadRemark() {
        return this.uploadRemark;
    }

    public FimCust uploadRemark(String uploadRemark) {
        this.setUploadRemark(uploadRemark);
        return this;
    }

    public void setUploadRemark(String uploadRemark) {
        this.uploadRemark = uploadRemark;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FimCust)) {
            return false;
        }
        return getId() != null && getId().equals(((FimCust) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FimCust{" +
            "id=" + getId() +
            ", custId='" + getCustId() + "'" +
            ", clientId='" + getClientId() + "'" +
            ", idType='" + getIdType() + "'" +
            ", ctryCode='" + getCtryCode() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTs='" + getCreatedTs() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedTs='" + getUpdatedTs() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", uploadRemark='" + getUploadRemark() + "'" +
            "}";
    }
}
