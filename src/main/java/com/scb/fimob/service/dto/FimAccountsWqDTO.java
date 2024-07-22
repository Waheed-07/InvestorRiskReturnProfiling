package com.scb.fimob.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.scb.fimob.domain.FimAccountsWq} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FimAccountsWqDTO implements Serializable {

    private Long id;

    private String accountId;

    @NotNull
    @Size(max = 30)
    private String custId;

    @NotNull
    @Size(max = 30)
    private String relnId;

    @NotNull
    @Size(max = 5)
    private String relnType;

    @Size(max = 10)
    private String operInst;

    @NotNull
    @Size(max = 30)
    private String isAcctNbr;

    @NotNull
    @Size(max = 30)
    private String bndAcctNbr;

    @Size(max = 10)
    private String closingId;

    @Size(max = 10)
    private String subSegment;

    @Size(max = 10)
    private String branchCode;

    @NotNull
    @Size(max = 10)
    private String acctStatus;

    @Size(max = 3)
    private String ctryCode;

    @Size(max = 100)
    private String acctOwners;

    @Size(max = 200)
    private String remarks;

    @Size(max = 8)
    private String createdBy;

    private Instant createdTs;

    @Size(max = 8)
    private String updatedBy;

    private Instant updatedTs;

    @Size(max = 10)
    private String recordStatus;

    private String uploadRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getRelnId() {
        return relnId;
    }

    public void setRelnId(String relnId) {
        this.relnId = relnId;
    }

    public String getRelnType() {
        return relnType;
    }

    public void setRelnType(String relnType) {
        this.relnType = relnType;
    }

    public String getOperInst() {
        return operInst;
    }

    public void setOperInst(String operInst) {
        this.operInst = operInst;
    }

    public String getIsAcctNbr() {
        return isAcctNbr;
    }

    public void setIsAcctNbr(String isAcctNbr) {
        this.isAcctNbr = isAcctNbr;
    }

    public String getBndAcctNbr() {
        return bndAcctNbr;
    }

    public void setBndAcctNbr(String bndAcctNbr) {
        this.bndAcctNbr = bndAcctNbr;
    }

    public String getClosingId() {
        return closingId;
    }

    public void setClosingId(String closingId) {
        this.closingId = closingId;
    }

    public String getSubSegment() {
        return subSegment;
    }

    public void setSubSegment(String subSegment) {
        this.subSegment = subSegment;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(String acctStatus) {
        this.acctStatus = acctStatus;
    }

    public String getCtryCode() {
        return ctryCode;
    }

    public void setCtryCode(String ctryCode) {
        this.ctryCode = ctryCode;
    }

    public String getAcctOwners() {
        return acctOwners;
    }

    public void setAcctOwners(String acctOwners) {
        this.acctOwners = acctOwners;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getUploadRemark() {
        return uploadRemark;
    }

    public void setUploadRemark(String uploadRemark) {
        this.uploadRemark = uploadRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FimAccountsWqDTO)) {
            return false;
        }

        FimAccountsWqDTO fimAccountsWqDTO = (FimAccountsWqDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fimAccountsWqDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FimAccountsWqDTO{" +
            "id=" + getId() +
            ", accountId='" + getAccountId() + "'" +
            ", custId='" + getCustId() + "'" +
            ", relnId='" + getRelnId() + "'" +
            ", relnType='" + getRelnType() + "'" +
            ", operInst='" + getOperInst() + "'" +
            ", isAcctNbr='" + getIsAcctNbr() + "'" +
            ", bndAcctNbr='" + getBndAcctNbr() + "'" +
            ", closingId='" + getClosingId() + "'" +
            ", subSegment='" + getSubSegment() + "'" +
            ", branchCode='" + getBranchCode() + "'" +
            ", acctStatus='" + getAcctStatus() + "'" +
            ", ctryCode='" + getCtryCode() + "'" +
            ", acctOwners='" + getAcctOwners() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTs='" + getCreatedTs() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedTs='" + getUpdatedTs() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", uploadRemark='" + getUploadRemark() + "'" +
            "}";
    }
}
