package com.myapp.web.rest;

import static com.myapp.domain.FinancialDetailsAsserts.*;
import static com.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.IntegrationTest;
import com.myapp.domain.FinancialDetails;
import com.myapp.repository.FinancialDetailsRepository;
import com.myapp.service.dto.FinancialDetailsDTO;
import com.myapp.service.mapper.FinancialDetailsMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link FinancialDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FinancialDetailsResourceIT {

    private static final Double DEFAULT_ANNUAL_INCOME = 1D;
    private static final Double UPDATED_ANNUAL_INCOME = 2D;

    private static final Double DEFAULT_NET_WORTH = 1D;
    private static final Double UPDATED_NET_WORTH = 2D;

    private static final Double DEFAULT_CURRENT_SAVINGS = 1D;
    private static final Double UPDATED_CURRENT_SAVINGS = 2D;

    private static final String DEFAULT_INVESTMENT_EXPERIENCE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTMENT_EXPERIENCE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_OF_FUNDS = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_OF_FUNDS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/financial-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FinancialDetailsRepository financialDetailsRepository;

    @Autowired
    private FinancialDetailsMapper financialDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFinancialDetailsMockMvc;

    private FinancialDetails financialDetails;

    private FinancialDetails insertedFinancialDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinancialDetails createEntity(EntityManager em) {
        FinancialDetails financialDetails = new FinancialDetails()
            .annualIncome(DEFAULT_ANNUAL_INCOME)
            .netWorth(DEFAULT_NET_WORTH)
            .currentSavings(DEFAULT_CURRENT_SAVINGS)
            .investmentExperience(DEFAULT_INVESTMENT_EXPERIENCE)
            .sourceOfFunds(DEFAULT_SOURCE_OF_FUNDS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return financialDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinancialDetails createUpdatedEntity(EntityManager em) {
        FinancialDetails financialDetails = new FinancialDetails()
            .annualIncome(UPDATED_ANNUAL_INCOME)
            .netWorth(UPDATED_NET_WORTH)
            .currentSavings(UPDATED_CURRENT_SAVINGS)
            .investmentExperience(UPDATED_INVESTMENT_EXPERIENCE)
            .sourceOfFunds(UPDATED_SOURCE_OF_FUNDS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return financialDetails;
    }

    @BeforeEach
    public void initTest() {
        financialDetails = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFinancialDetails != null) {
            financialDetailsRepository.delete(insertedFinancialDetails);
            insertedFinancialDetails = null;
        }
    }

    @Test
    @Transactional
    void createFinancialDetails() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FinancialDetails
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);
        var returnedFinancialDetailsDTO = om.readValue(
            restFinancialDetailsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(financialDetailsDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FinancialDetailsDTO.class
        );

        // Validate the FinancialDetails in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFinancialDetails = financialDetailsMapper.toEntity(returnedFinancialDetailsDTO);
        assertFinancialDetailsUpdatableFieldsEquals(returnedFinancialDetails, getPersistedFinancialDetails(returnedFinancialDetails));

        insertedFinancialDetails = returnedFinancialDetails;
    }

    @Test
    @Transactional
    void createFinancialDetailsWithExistingId() throws Exception {
        // Create the FinancialDetails with an existing ID
        financialDetails.setId(1L);
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinancialDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(financialDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinancialDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAnnualIncomeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        financialDetails.setAnnualIncome(null);

        // Create the FinancialDetails, which fails.
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        restFinancialDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(financialDetailsDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNetWorthIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        financialDetails.setNetWorth(null);

        // Create the FinancialDetails, which fails.
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        restFinancialDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(financialDetailsDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInvestmentExperienceIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        financialDetails.setInvestmentExperience(null);

        // Create the FinancialDetails, which fails.
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        restFinancialDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(financialDetailsDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSourceOfFundsIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        financialDetails.setSourceOfFunds(null);

        // Create the FinancialDetails, which fails.
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        restFinancialDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(financialDetailsDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        financialDetails.setCreatedAt(null);

        // Create the FinancialDetails, which fails.
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        restFinancialDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(financialDetailsDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFinancialDetails() throws Exception {
        // Initialize the database
        insertedFinancialDetails = financialDetailsRepository.saveAndFlush(financialDetails);

        // Get all the financialDetailsList
        restFinancialDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(financialDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].annualIncome").value(hasItem(DEFAULT_ANNUAL_INCOME.doubleValue())))
            .andExpect(jsonPath("$.[*].netWorth").value(hasItem(DEFAULT_NET_WORTH.doubleValue())))
            .andExpect(jsonPath("$.[*].currentSavings").value(hasItem(DEFAULT_CURRENT_SAVINGS.doubleValue())))
            .andExpect(jsonPath("$.[*].investmentExperience").value(hasItem(DEFAULT_INVESTMENT_EXPERIENCE)))
            .andExpect(jsonPath("$.[*].sourceOfFunds").value(hasItem(DEFAULT_SOURCE_OF_FUNDS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getFinancialDetails() throws Exception {
        // Initialize the database
        insertedFinancialDetails = financialDetailsRepository.saveAndFlush(financialDetails);

        // Get the financialDetails
        restFinancialDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, financialDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(financialDetails.getId().intValue()))
            .andExpect(jsonPath("$.annualIncome").value(DEFAULT_ANNUAL_INCOME.doubleValue()))
            .andExpect(jsonPath("$.netWorth").value(DEFAULT_NET_WORTH.doubleValue()))
            .andExpect(jsonPath("$.currentSavings").value(DEFAULT_CURRENT_SAVINGS.doubleValue()))
            .andExpect(jsonPath("$.investmentExperience").value(DEFAULT_INVESTMENT_EXPERIENCE))
            .andExpect(jsonPath("$.sourceOfFunds").value(DEFAULT_SOURCE_OF_FUNDS))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingFinancialDetails() throws Exception {
        // Get the financialDetails
        restFinancialDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFinancialDetails() throws Exception {
        // Initialize the database
        insertedFinancialDetails = financialDetailsRepository.saveAndFlush(financialDetails);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the financialDetails
        FinancialDetails updatedFinancialDetails = financialDetailsRepository.findById(financialDetails.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFinancialDetails are not directly saved in db
        em.detach(updatedFinancialDetails);
        updatedFinancialDetails
            .annualIncome(UPDATED_ANNUAL_INCOME)
            .netWorth(UPDATED_NET_WORTH)
            .currentSavings(UPDATED_CURRENT_SAVINGS)
            .investmentExperience(UPDATED_INVESTMENT_EXPERIENCE)
            .sourceOfFunds(UPDATED_SOURCE_OF_FUNDS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(updatedFinancialDetails);

        restFinancialDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, financialDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(financialDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the FinancialDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFinancialDetailsToMatchAllProperties(updatedFinancialDetails);
    }

    @Test
    @Transactional
    void putNonExistingFinancialDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        financialDetails.setId(longCount.incrementAndGet());

        // Create the FinancialDetails
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFinancialDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, financialDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(financialDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FinancialDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFinancialDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        financialDetails.setId(longCount.incrementAndGet());

        // Create the FinancialDetails
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFinancialDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(financialDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FinancialDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFinancialDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        financialDetails.setId(longCount.incrementAndGet());

        // Create the FinancialDetails
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFinancialDetailsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(financialDetailsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FinancialDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFinancialDetailsWithPatch() throws Exception {
        // Initialize the database
        insertedFinancialDetails = financialDetailsRepository.saveAndFlush(financialDetails);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the financialDetails using partial update
        FinancialDetails partialUpdatedFinancialDetails = new FinancialDetails();
        partialUpdatedFinancialDetails.setId(financialDetails.getId());

        partialUpdatedFinancialDetails.sourceOfFunds(UPDATED_SOURCE_OF_FUNDS);

        restFinancialDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFinancialDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFinancialDetails))
            )
            .andExpect(status().isOk());

        // Validate the FinancialDetails in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFinancialDetailsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFinancialDetails, financialDetails),
            getPersistedFinancialDetails(financialDetails)
        );
    }

    @Test
    @Transactional
    void fullUpdateFinancialDetailsWithPatch() throws Exception {
        // Initialize the database
        insertedFinancialDetails = financialDetailsRepository.saveAndFlush(financialDetails);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the financialDetails using partial update
        FinancialDetails partialUpdatedFinancialDetails = new FinancialDetails();
        partialUpdatedFinancialDetails.setId(financialDetails.getId());

        partialUpdatedFinancialDetails
            .annualIncome(UPDATED_ANNUAL_INCOME)
            .netWorth(UPDATED_NET_WORTH)
            .currentSavings(UPDATED_CURRENT_SAVINGS)
            .investmentExperience(UPDATED_INVESTMENT_EXPERIENCE)
            .sourceOfFunds(UPDATED_SOURCE_OF_FUNDS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restFinancialDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFinancialDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFinancialDetails))
            )
            .andExpect(status().isOk());

        // Validate the FinancialDetails in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFinancialDetailsUpdatableFieldsEquals(
            partialUpdatedFinancialDetails,
            getPersistedFinancialDetails(partialUpdatedFinancialDetails)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFinancialDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        financialDetails.setId(longCount.incrementAndGet());

        // Create the FinancialDetails
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFinancialDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, financialDetailsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(financialDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FinancialDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFinancialDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        financialDetails.setId(longCount.incrementAndGet());

        // Create the FinancialDetails
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFinancialDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(financialDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FinancialDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFinancialDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        financialDetails.setId(longCount.incrementAndGet());

        // Create the FinancialDetails
        FinancialDetailsDTO financialDetailsDTO = financialDetailsMapper.toDto(financialDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFinancialDetailsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(financialDetailsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FinancialDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFinancialDetails() throws Exception {
        // Initialize the database
        insertedFinancialDetails = financialDetailsRepository.saveAndFlush(financialDetails);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the financialDetails
        restFinancialDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, financialDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return financialDetailsRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected FinancialDetails getPersistedFinancialDetails(FinancialDetails financialDetails) {
        return financialDetailsRepository.findById(financialDetails.getId()).orElseThrow();
    }

    protected void assertPersistedFinancialDetailsToMatchAllProperties(FinancialDetails expectedFinancialDetails) {
        assertFinancialDetailsAllPropertiesEquals(expectedFinancialDetails, getPersistedFinancialDetails(expectedFinancialDetails));
    }

    protected void assertPersistedFinancialDetailsToMatchUpdatableProperties(FinancialDetails expectedFinancialDetails) {
        assertFinancialDetailsAllUpdatablePropertiesEquals(
            expectedFinancialDetails,
            getPersistedFinancialDetails(expectedFinancialDetails)
        );
    }
}
