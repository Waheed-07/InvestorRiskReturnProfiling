package com.myapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FinancialDetails.
 */
@Entity
@Table(name = "financial_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FinancialDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "annual_income", nullable = false)
    private Double annualIncome;

    @NotNull
    @Column(name = "net_worth", nullable = false)
    private Double netWorth;

    @Column(name = "current_savings")
    private Double currentSavings;

    @NotNull
    @Column(name = "investment_experience", nullable = false)
    private String investmentExperience;

    @NotNull
    @Column(name = "source_of_funds", nullable = false)
    private String sourceOfFunds;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FinancialDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAnnualIncome() {
        return this.annualIncome;
    }

    public FinancialDetails annualIncome(Double annualIncome) {
        this.setAnnualIncome(annualIncome);
        return this;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Double getNetWorth() {
        return this.netWorth;
    }

    public FinancialDetails netWorth(Double netWorth) {
        this.setNetWorth(netWorth);
        return this;
    }

    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    public Double getCurrentSavings() {
        return this.currentSavings;
    }

    public FinancialDetails currentSavings(Double currentSavings) {
        this.setCurrentSavings(currentSavings);
        return this;
    }

    public void setCurrentSavings(Double currentSavings) {
        this.currentSavings = currentSavings;
    }

    public String getInvestmentExperience() {
        return this.investmentExperience;
    }

    public FinancialDetails investmentExperience(String investmentExperience) {
        this.setInvestmentExperience(investmentExperience);
        return this;
    }

    public void setInvestmentExperience(String investmentExperience) {
        this.investmentExperience = investmentExperience;
    }

    public String getSourceOfFunds() {
        return this.sourceOfFunds;
    }

    public FinancialDetails sourceOfFunds(String sourceOfFunds) {
        this.setSourceOfFunds(sourceOfFunds);
        return this;
    }

    public void setSourceOfFunds(String sourceOfFunds) {
        this.sourceOfFunds = sourceOfFunds;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public FinancialDetails createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public FinancialDetails updatedAt(Instant updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinancialDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((FinancialDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FinancialDetails{" +
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
