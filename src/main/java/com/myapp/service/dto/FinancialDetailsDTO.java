package com.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.myapp.domain.FinancialDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FinancialDetailsDTO implements Serializable {

    private Long id;

    @NotNull
    private Double annualIncome;

    @NotNull
    private Double netWorth;

    private Double currentSavings;

    @NotNull
    private String investmentExperience;

    @NotNull
    private String sourceOfFunds;

    @NotNull
    private Instant createdAt;

    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    public Double getCurrentSavings() {
        return currentSavings;
    }

    public void setCurrentSavings(Double currentSavings) {
        this.currentSavings = currentSavings;
    }

    public String getInvestmentExperience() {
        return investmentExperience;
    }

    public void setInvestmentExperience(String investmentExperience) {
        this.investmentExperience = investmentExperience;
    }

    public String getSourceOfFunds() {
        return sourceOfFunds;
    }

    public void setSourceOfFunds(String sourceOfFunds) {
        this.sourceOfFunds = sourceOfFunds;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinancialDetailsDTO)) {
            return false;
        }

        FinancialDetailsDTO financialDetailsDTO = (FinancialDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, financialDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FinancialDetailsDTO{" +
            "id=" + getId() +
            ", annualIncome=" + getAnnualIncome() +
            ", netWorth=" + getNetWorth() +
            ", currentSavings=" + getCurrentSavings() +
            ", investmentExperience='" + getInvestmentExperience() + "'" +
            ", sourceOfFunds='" + getSourceOfFunds() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
