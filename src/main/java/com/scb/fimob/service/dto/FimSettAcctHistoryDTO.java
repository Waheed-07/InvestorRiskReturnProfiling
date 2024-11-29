package com.scb.fimob.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.scb.fimob.domain.FimSettAcctHistory} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FimSettAcctHistoryDTO implements Serializable {

    private Long id;

    private String settaccId;

    private Instant historyTs;

    @NotNull
    @Size(max = 15)
    private String accountId;

    @NotNull
    @Size(max = 30)
    private String settAcctNbr;

    @NotNull
    @Size(max = 3)
    private String settCcy;

    @NotNull
    @Size(max = 10)
    private String settAcctStatus;

    @Size(max = 8)
    private String createdBy;

    private Instant createdTs;

    @Size(max = 8)
    private String updatedBy;

    private Instant updatedTs;

    @Size(max = 10)
    private String recordStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettaccId() {
        return settaccId;
    }

    public void setSettaccId(String settaccId) {
        this.settaccId = settaccId;
    }

    public Instant getHistoryTs() {
        return historyTs;
    }

    public void setHistoryTs(Instant historyTs) {
        this.historyTs = historyTs;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSettAcctNbr() {
        return settAcctNbr;
    }

    public void setSettAcctNbr(String settAcctNbr) {
        this.settAcctNbr = settAcctNbr;
    }

    public String getSettCcy() {
        return settCcy;
    }

    public void setSettCcy(String settCcy) {
        this.settCcy = settCcy;
    }

    public String getSettAcctStatus() {
        return settAcctStatus;
    }

    public void setSettAcctStatus(String settAcctStatus) {
        this.settAcctStatus = settAcctStatus;
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
        if (!(o instanceof FimSettAcctHistoryDTO)) {
            return false;
        }

        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = (FimSettAcctHistoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fimSettAcctHistoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FimSettAcctHistoryDTO{" +
            "id=" + getId() +
            ", settaccId='" + getSettaccId() + "'" +
            ", historyTs='" + getHistoryTs() + "'" +
            ", accountId='" + getAccountId() + "'" +
            ", settAcctNbr='" + getSettAcctNbr() + "'" +
            ", settCcy='" + getSettCcy() + "'" +
            ", settAcctStatus='" + getSettAcctStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTs='" + getCreatedTs() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedTs='" + getUpdatedTs() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            "}";
    }
}
