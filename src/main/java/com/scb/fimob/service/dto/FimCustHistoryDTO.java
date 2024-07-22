package com.scb.fimob.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.scb.fimob.domain.FimCustHistory} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FimCustHistoryDTO implements Serializable {

    private Long id;

    private String custId;

    private Instant historyTs;

    @NotNull
    @Size(max = 30)
    private String clientId;

    @NotNull
    @Size(max = 10)
    private String idType;

    @NotNull
    @Size(max = 3)
    private String ctryCode;

    @NotNull
    @Size(max = 8)
    private String createdBy;

    private Instant createdTs;

    @Size(max = 8)
    private String updatedBy;

    private Instant updatedTs;

    private String recordStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Instant getHistoryTs() {
        return historyTs;
    }

    public void setHistoryTs(Instant historyTs) {
        this.historyTs = historyTs;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCtryCode() {
        return ctryCode;
    }

    public void setCtryCode(String ctryCode) {
        this.ctryCode = ctryCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Instant createdTs) {
        this.createdTs = createdTs;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(Instant updatedTs) {
        this.updatedTs = updatedTs;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FimCustHistoryDTO)) {
            return false;
        }

        FimCustHistoryDTO fimCustHistoryDTO = (FimCustHistoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fimCustHistoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FimCustHistoryDTO{" +
            "id=" + getId() +
            ", custId='" + getCustId() + "'" +
            ", historyTs='" + getHistoryTs() + "'" +
            ", clientId='" + getClientId() + "'" +
            ", idType='" + getIdType() + "'" +
            ", ctryCode='" + getCtryCode() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTs='" + getCreatedTs() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedTs='" + getUpdatedTs() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            "}";
    }
}
