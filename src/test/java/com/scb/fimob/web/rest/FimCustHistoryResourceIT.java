package com.scb.fimob.web.rest;

import static com.scb.fimob.domain.FimCustHistoryAsserts.*;
import static com.scb.fimob.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.fimob.IntegrationTest;
import com.scb.fimob.domain.FimCustHistory;
import com.scb.fimob.repository.FimCustHistoryRepository;
import com.scb.fimob.service.dto.FimCustHistoryDTO;
import com.scb.fimob.service.mapper.FimCustHistoryMapper;
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
 * Integration tests for the {@link FimCustHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FimCustHistoryResourceIT {

    private static final String DEFAULT_CUST_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_HISTORY_TS = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HISTORY_TS = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ID_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ID_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CTRY_CODE = "AAA";
    private static final String UPDATED_CTRY_CODE = "BBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBB";

    private static final Instant DEFAULT_CREATED_TS = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_TS = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBB";

    private static final Instant DEFAULT_UPDATED_TS = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_TS = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_RECORD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fim-cust-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FimCustHistoryRepository fimCustHistoryRepository;

    @Autowired
    private FimCustHistoryMapper fimCustHistoryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFimCustHistoryMockMvc;

    private FimCustHistory fimCustHistory;

    private FimCustHistory insertedFimCustHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimCustHistory createEntity(EntityManager em) {
        FimCustHistory fimCustHistory = new FimCustHistory()
            .custId(DEFAULT_CUST_ID)
            .historyTs(DEFAULT_HISTORY_TS)
            .clientId(DEFAULT_CLIENT_ID)
            .idType(DEFAULT_ID_TYPE)
            .ctryCode(DEFAULT_CTRY_CODE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTs(DEFAULT_CREATED_TS)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedTs(DEFAULT_UPDATED_TS)
            .recordStatus(DEFAULT_RECORD_STATUS);
        return fimCustHistory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimCustHistory createUpdatedEntity(EntityManager em) {
        FimCustHistory fimCustHistory = new FimCustHistory()
            .custId(UPDATED_CUST_ID)
            .historyTs(UPDATED_HISTORY_TS)
            .clientId(UPDATED_CLIENT_ID)
            .idType(UPDATED_ID_TYPE)
            .ctryCode(UPDATED_CTRY_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS);
        return fimCustHistory;
    }

    @BeforeEach
    public void initTest() {
        fimCustHistory = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFimCustHistory != null) {
            fimCustHistoryRepository.delete(insertedFimCustHistory);
            insertedFimCustHistory = null;
        }
    }

    @Test
    @Transactional
    void createFimCustHistory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FimCustHistory
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);
        var returnedFimCustHistoryDTO = om.readValue(
            restFimCustHistoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustHistoryDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FimCustHistoryDTO.class
        );

        // Validate the FimCustHistory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFimCustHistory = fimCustHistoryMapper.toEntity(returnedFimCustHistoryDTO);
        assertFimCustHistoryUpdatableFieldsEquals(returnedFimCustHistory, getPersistedFimCustHistory(returnedFimCustHistory));

        insertedFimCustHistory = returnedFimCustHistory;
    }

    @Test
    @Transactional
    void createFimCustHistoryWithExistingId() throws Exception {
        // Create the FimCustHistory with an existing ID
        fimCustHistory.setId(1L);
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFimCustHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FimCustHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkClientIdIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCustHistory.setClientId(null);

        // Create the FimCustHistory, which fails.
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        restFimCustHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustHistoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIdTypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCustHistory.setIdType(null);

        // Create the FimCustHistory, which fails.
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        restFimCustHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustHistoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCtryCodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCustHistory.setCtryCode(null);

        // Create the FimCustHistory, which fails.
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        restFimCustHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustHistoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCustHistory.setCreatedBy(null);

        // Create the FimCustHistory, which fails.
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        restFimCustHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustHistoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFimCustHistories() throws Exception {
        // Initialize the database
        insertedFimCustHistory = fimCustHistoryRepository.saveAndFlush(fimCustHistory);

        // Get all the fimCustHistoryList
        restFimCustHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fimCustHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].custId").value(hasItem(DEFAULT_CUST_ID)))
            .andExpect(jsonPath("$.[*].historyTs").value(hasItem(DEFAULT_HISTORY_TS.toString())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].idType").value(hasItem(DEFAULT_ID_TYPE)))
            .andExpect(jsonPath("$.[*].ctryCode").value(hasItem(DEFAULT_CTRY_CODE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTs").value(hasItem(DEFAULT_CREATED_TS.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedTs").value(hasItem(DEFAULT_UPDATED_TS.toString())))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)));
    }

    @Test
    @Transactional
    void getFimCustHistory() throws Exception {
        // Initialize the database
        insertedFimCustHistory = fimCustHistoryRepository.saveAndFlush(fimCustHistory);

        // Get the fimCustHistory
        restFimCustHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, fimCustHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fimCustHistory.getId().intValue()))
            .andExpect(jsonPath("$.custId").value(DEFAULT_CUST_ID))
            .andExpect(jsonPath("$.historyTs").value(DEFAULT_HISTORY_TS.toString()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID))
            .andExpect(jsonPath("$.idType").value(DEFAULT_ID_TYPE))
            .andExpect(jsonPath("$.ctryCode").value(DEFAULT_CTRY_CODE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTs").value(DEFAULT_CREATED_TS.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedTs").value(DEFAULT_UPDATED_TS.toString()))
            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingFimCustHistory() throws Exception {
        // Get the fimCustHistory
        restFimCustHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFimCustHistory() throws Exception {
        // Initialize the database
        insertedFimCustHistory = fimCustHistoryRepository.saveAndFlush(fimCustHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimCustHistory
        FimCustHistory updatedFimCustHistory = fimCustHistoryRepository.findById(fimCustHistory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFimCustHistory are not directly saved in db
        em.detach(updatedFimCustHistory);
        updatedFimCustHistory
            .custId(UPDATED_CUST_ID)
            .historyTs(UPDATED_HISTORY_TS)
            .clientId(UPDATED_CLIENT_ID)
            .idType(UPDATED_ID_TYPE)
            .ctryCode(UPDATED_CTRY_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS);
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(updatedFimCustHistory);

        restFimCustHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimCustHistoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimCustHistoryDTO))
            )
            .andExpect(status().isOk());

        // Validate the FimCustHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFimCustHistoryToMatchAllProperties(updatedFimCustHistory);
    }

    @Test
    @Transactional
    void putNonExistingFimCustHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustHistory.setId(longCount.incrementAndGet());

        // Create the FimCustHistory
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimCustHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimCustHistoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimCustHistoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCustHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFimCustHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustHistory.setId(longCount.incrementAndGet());

        // Create the FimCustHistory
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimCustHistoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCustHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFimCustHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustHistory.setId(longCount.incrementAndGet());

        // Create the FimCustHistory
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustHistoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustHistoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimCustHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFimCustHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedFimCustHistory = fimCustHistoryRepository.saveAndFlush(fimCustHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimCustHistory using partial update
        FimCustHistory partialUpdatedFimCustHistory = new FimCustHistory();
        partialUpdatedFimCustHistory.setId(fimCustHistory.getId());

        partialUpdatedFimCustHistory
            .custId(UPDATED_CUST_ID)
            .historyTs(UPDATED_HISTORY_TS)
            .clientId(UPDATED_CLIENT_ID)
            .ctryCode(UPDATED_CTRY_CODE)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS);

        restFimCustHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimCustHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimCustHistory))
            )
            .andExpect(status().isOk());

        // Validate the FimCustHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimCustHistoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFimCustHistory, fimCustHistory),
            getPersistedFimCustHistory(fimCustHistory)
        );
    }

    @Test
    @Transactional
    void fullUpdateFimCustHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedFimCustHistory = fimCustHistoryRepository.saveAndFlush(fimCustHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimCustHistory using partial update
        FimCustHistory partialUpdatedFimCustHistory = new FimCustHistory();
        partialUpdatedFimCustHistory.setId(fimCustHistory.getId());

        partialUpdatedFimCustHistory
            .custId(UPDATED_CUST_ID)
            .historyTs(UPDATED_HISTORY_TS)
            .clientId(UPDATED_CLIENT_ID)
            .idType(UPDATED_ID_TYPE)
            .ctryCode(UPDATED_CTRY_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS);

        restFimCustHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimCustHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimCustHistory))
            )
            .andExpect(status().isOk());

        // Validate the FimCustHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimCustHistoryUpdatableFieldsEquals(partialUpdatedFimCustHistory, getPersistedFimCustHistory(partialUpdatedFimCustHistory));
    }

    @Test
    @Transactional
    void patchNonExistingFimCustHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustHistory.setId(longCount.incrementAndGet());

        // Create the FimCustHistory
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimCustHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fimCustHistoryDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimCustHistoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCustHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFimCustHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustHistory.setId(longCount.incrementAndGet());

        // Create the FimCustHistory
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimCustHistoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCustHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFimCustHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustHistory.setId(longCount.incrementAndGet());

        // Create the FimCustHistory
        FimCustHistoryDTO fimCustHistoryDTO = fimCustHistoryMapper.toDto(fimCustHistory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustHistoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fimCustHistoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimCustHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFimCustHistory() throws Exception {
        // Initialize the database
        insertedFimCustHistory = fimCustHistoryRepository.saveAndFlush(fimCustHistory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fimCustHistory
        restFimCustHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, fimCustHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fimCustHistoryRepository.count();
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

    protected FimCustHistory getPersistedFimCustHistory(FimCustHistory fimCustHistory) {
        return fimCustHistoryRepository.findById(fimCustHistory.getId()).orElseThrow();
    }

    protected void assertPersistedFimCustHistoryToMatchAllProperties(FimCustHistory expectedFimCustHistory) {
        assertFimCustHistoryAllPropertiesEquals(expectedFimCustHistory, getPersistedFimCustHistory(expectedFimCustHistory));
    }

    protected void assertPersistedFimCustHistoryToMatchUpdatableProperties(FimCustHistory expectedFimCustHistory) {
        assertFimCustHistoryAllUpdatablePropertiesEquals(expectedFimCustHistory, getPersistedFimCustHistory(expectedFimCustHistory));
    }
}
