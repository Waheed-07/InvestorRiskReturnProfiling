package com.scb.fimob.web.rest;

import static com.scb.fimob.domain.FimSettAcctHistoryAsserts.*;
import static com.scb.fimob.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.fimob.IntegrationTest;
import com.scb.fimob.domain.FimSettAcctHistory;
import com.scb.fimob.repository.FimSettAcctHistoryRepository;
import com.scb.fimob.service.dto.FimSettAcctHistoryDTO;
import com.scb.fimob.service.mapper.FimSettAcctHistoryMapper;
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
 * Integration tests for the {@link FimSettAcctHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FimSettAcctHistoryResourceIT {

    private static final String DEFAULT_SETTACC_ID = "AAAAAAAAAA";
    private static final String UPDATED_SETTACC_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_HISTORY_TS = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HISTORY_TS = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SETT_ACCT_NBR = "AAAAAAAAAA";
    private static final String UPDATED_SETT_ACCT_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_SETT_CCY = "AAA";
    private static final String UPDATED_SETT_CCY = "BBB";

    private static final String DEFAULT_SETT_ACCT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SETT_ACCT_STATUS = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/fim-sett-acct-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FimSettAcctHistoryRepository fimSettAcctHistoryRepository;

    @Autowired
    private FimSettAcctHistoryMapper fimSettAcctHistoryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFimSettAcctHistoryMockMvc;

    private FimSettAcctHistory fimSettAcctHistory;

    private FimSettAcctHistory insertedFimSettAcctHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimSettAcctHistory createEntity(EntityManager em) {
        FimSettAcctHistory fimSettAcctHistory = new FimSettAcctHistory()
            .settaccId(DEFAULT_SETTACC_ID)
            .historyTs(DEFAULT_HISTORY_TS)
            .accountId(DEFAULT_ACCOUNT_ID)
            .settAcctNbr(DEFAULT_SETT_ACCT_NBR)
            .settCcy(DEFAULT_SETT_CCY)
            .settAcctStatus(DEFAULT_SETT_ACCT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTs(DEFAULT_CREATED_TS)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedTs(DEFAULT_UPDATED_TS)
            .recordStatus(DEFAULT_RECORD_STATUS);
        return fimSettAcctHistory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimSettAcctHistory createUpdatedEntity(EntityManager em) {
        FimSettAcctHistory fimSettAcctHistory = new FimSettAcctHistory()
            .settaccId(UPDATED_SETTACC_ID)
            .historyTs(UPDATED_HISTORY_TS)
            .accountId(UPDATED_ACCOUNT_ID)
            .settAcctNbr(UPDATED_SETT_ACCT_NBR)
            .settCcy(UPDATED_SETT_CCY)
            .settAcctStatus(UPDATED_SETT_ACCT_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS);
        return fimSettAcctHistory;
    }

    @BeforeEach
    public void initTest() {
        fimSettAcctHistory = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFimSettAcctHistory != null) {
            fimSettAcctHistoryRepository.delete(insertedFimSettAcctHistory);
            insertedFimSettAcctHistory = null;
        }
    }

    @Test
    @Transactional
    void createFimSettAcctHistory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FimSettAcctHistory
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);
        var returnedFimSettAcctHistoryDTO = om.readValue(
            restFimSettAcctHistoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctHistoryDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FimSettAcctHistoryDTO.class
        );

        // Validate the FimSettAcctHistory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFimSettAcctHistory = fimSettAcctHistoryMapper.toEntity(returnedFimSettAcctHistoryDTO);
        assertFimSettAcctHistoryUpdatableFieldsEquals(
            returnedFimSettAcctHistory,
            getPersistedFimSettAcctHistory(returnedFimSettAcctHistory)
        );

        insertedFimSettAcctHistory = returnedFimSettAcctHistory;
    }

    @Test
    @Transactional
    void createFimSettAcctHistoryWithExistingId() throws Exception {
        // Create the FimSettAcctHistory with an existing ID
        fimSettAcctHistory.setId(1L);
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFimSettAcctHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAccountIdIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimSettAcctHistory.setAccountId(null);

        // Create the FimSettAcctHistory, which fails.
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        restFimSettAcctHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctHistoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSettAcctNbrIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimSettAcctHistory.setSettAcctNbr(null);

        // Create the FimSettAcctHistory, which fails.
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        restFimSettAcctHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctHistoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSettCcyIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimSettAcctHistory.setSettCcy(null);

        // Create the FimSettAcctHistory, which fails.
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        restFimSettAcctHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctHistoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSettAcctStatusIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimSettAcctHistory.setSettAcctStatus(null);

        // Create the FimSettAcctHistory, which fails.
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        restFimSettAcctHistoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctHistoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFimSettAcctHistories() throws Exception {
        // Initialize the database
        insertedFimSettAcctHistory = fimSettAcctHistoryRepository.saveAndFlush(fimSettAcctHistory);

        // Get all the fimSettAcctHistoryList
        restFimSettAcctHistoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fimSettAcctHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].settaccId").value(hasItem(DEFAULT_SETTACC_ID)))
            .andExpect(jsonPath("$.[*].historyTs").value(hasItem(DEFAULT_HISTORY_TS.toString())))
            .andExpect(jsonPath("$.[*].accountId").value(hasItem(DEFAULT_ACCOUNT_ID)))
            .andExpect(jsonPath("$.[*].settAcctNbr").value(hasItem(DEFAULT_SETT_ACCT_NBR)))
            .andExpect(jsonPath("$.[*].settCcy").value(hasItem(DEFAULT_SETT_CCY)))
            .andExpect(jsonPath("$.[*].settAcctStatus").value(hasItem(DEFAULT_SETT_ACCT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTs").value(hasItem(DEFAULT_CREATED_TS.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedTs").value(hasItem(DEFAULT_UPDATED_TS.toString())))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)));
    }

    @Test
    @Transactional
    void getFimSettAcctHistory() throws Exception {
        // Initialize the database
        insertedFimSettAcctHistory = fimSettAcctHistoryRepository.saveAndFlush(fimSettAcctHistory);

        // Get the fimSettAcctHistory
        restFimSettAcctHistoryMockMvc
            .perform(get(ENTITY_API_URL_ID, fimSettAcctHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fimSettAcctHistory.getId().intValue()))
            .andExpect(jsonPath("$.settaccId").value(DEFAULT_SETTACC_ID))
            .andExpect(jsonPath("$.historyTs").value(DEFAULT_HISTORY_TS.toString()))
            .andExpect(jsonPath("$.accountId").value(DEFAULT_ACCOUNT_ID))
            .andExpect(jsonPath("$.settAcctNbr").value(DEFAULT_SETT_ACCT_NBR))
            .andExpect(jsonPath("$.settCcy").value(DEFAULT_SETT_CCY))
            .andExpect(jsonPath("$.settAcctStatus").value(DEFAULT_SETT_ACCT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTs").value(DEFAULT_CREATED_TS.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedTs").value(DEFAULT_UPDATED_TS.toString()))
            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingFimSettAcctHistory() throws Exception {
        // Get the fimSettAcctHistory
        restFimSettAcctHistoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFimSettAcctHistory() throws Exception {
        // Initialize the database
        insertedFimSettAcctHistory = fimSettAcctHistoryRepository.saveAndFlush(fimSettAcctHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimSettAcctHistory
        FimSettAcctHistory updatedFimSettAcctHistory = fimSettAcctHistoryRepository.findById(fimSettAcctHistory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFimSettAcctHistory are not directly saved in db
        em.detach(updatedFimSettAcctHistory);
        updatedFimSettAcctHistory
            .settaccId(UPDATED_SETTACC_ID)
            .historyTs(UPDATED_HISTORY_TS)
            .accountId(UPDATED_ACCOUNT_ID)
            .settAcctNbr(UPDATED_SETT_ACCT_NBR)
            .settCcy(UPDATED_SETT_CCY)
            .settAcctStatus(UPDATED_SETT_ACCT_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS);
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(updatedFimSettAcctHistory);

        restFimSettAcctHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimSettAcctHistoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimSettAcctHistoryDTO))
            )
            .andExpect(status().isOk());

        // Validate the FimSettAcctHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFimSettAcctHistoryToMatchAllProperties(updatedFimSettAcctHistory);
    }

    @Test
    @Transactional
    void putNonExistingFimSettAcctHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctHistory.setId(longCount.incrementAndGet());

        // Create the FimSettAcctHistory
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimSettAcctHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimSettAcctHistoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimSettAcctHistoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFimSettAcctHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctHistory.setId(longCount.incrementAndGet());

        // Create the FimSettAcctHistory
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimSettAcctHistoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimSettAcctHistoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFimSettAcctHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctHistory.setId(longCount.incrementAndGet());

        // Create the FimSettAcctHistory
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimSettAcctHistoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctHistoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimSettAcctHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFimSettAcctHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedFimSettAcctHistory = fimSettAcctHistoryRepository.saveAndFlush(fimSettAcctHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimSettAcctHistory using partial update
        FimSettAcctHistory partialUpdatedFimSettAcctHistory = new FimSettAcctHistory();
        partialUpdatedFimSettAcctHistory.setId(fimSettAcctHistory.getId());

        partialUpdatedFimSettAcctHistory
            .accountId(UPDATED_ACCOUNT_ID)
            .settAcctNbr(UPDATED_SETT_ACCT_NBR)
            .settCcy(UPDATED_SETT_CCY)
            .settAcctStatus(UPDATED_SETT_ACCT_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS);

        restFimSettAcctHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimSettAcctHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimSettAcctHistory))
            )
            .andExpect(status().isOk());

        // Validate the FimSettAcctHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimSettAcctHistoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFimSettAcctHistory, fimSettAcctHistory),
            getPersistedFimSettAcctHistory(fimSettAcctHistory)
        );
    }

    @Test
    @Transactional
    void fullUpdateFimSettAcctHistoryWithPatch() throws Exception {
        // Initialize the database
        insertedFimSettAcctHistory = fimSettAcctHistoryRepository.saveAndFlush(fimSettAcctHistory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimSettAcctHistory using partial update
        FimSettAcctHistory partialUpdatedFimSettAcctHistory = new FimSettAcctHistory();
        partialUpdatedFimSettAcctHistory.setId(fimSettAcctHistory.getId());

        partialUpdatedFimSettAcctHistory
            .settaccId(UPDATED_SETTACC_ID)
            .historyTs(UPDATED_HISTORY_TS)
            .accountId(UPDATED_ACCOUNT_ID)
            .settAcctNbr(UPDATED_SETT_ACCT_NBR)
            .settCcy(UPDATED_SETT_CCY)
            .settAcctStatus(UPDATED_SETT_ACCT_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS);

        restFimSettAcctHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimSettAcctHistory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimSettAcctHistory))
            )
            .andExpect(status().isOk());

        // Validate the FimSettAcctHistory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimSettAcctHistoryUpdatableFieldsEquals(
            partialUpdatedFimSettAcctHistory,
            getPersistedFimSettAcctHistory(partialUpdatedFimSettAcctHistory)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFimSettAcctHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctHistory.setId(longCount.incrementAndGet());

        // Create the FimSettAcctHistory
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimSettAcctHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fimSettAcctHistoryDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimSettAcctHistoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFimSettAcctHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctHistory.setId(longCount.incrementAndGet());

        // Create the FimSettAcctHistory
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimSettAcctHistoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimSettAcctHistoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFimSettAcctHistory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctHistory.setId(longCount.incrementAndGet());

        // Create the FimSettAcctHistory
        FimSettAcctHistoryDTO fimSettAcctHistoryDTO = fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimSettAcctHistoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fimSettAcctHistoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimSettAcctHistory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFimSettAcctHistory() throws Exception {
        // Initialize the database
        insertedFimSettAcctHistory = fimSettAcctHistoryRepository.saveAndFlush(fimSettAcctHistory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fimSettAcctHistory
        restFimSettAcctHistoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, fimSettAcctHistory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fimSettAcctHistoryRepository.count();
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

    protected FimSettAcctHistory getPersistedFimSettAcctHistory(FimSettAcctHistory fimSettAcctHistory) {
        return fimSettAcctHistoryRepository.findById(fimSettAcctHistory.getId()).orElseThrow();
    }

    protected void assertPersistedFimSettAcctHistoryToMatchAllProperties(FimSettAcctHistory expectedFimSettAcctHistory) {
        assertFimSettAcctHistoryAllPropertiesEquals(expectedFimSettAcctHistory, getPersistedFimSettAcctHistory(expectedFimSettAcctHistory));
    }

    protected void assertPersistedFimSettAcctHistoryToMatchUpdatableProperties(FimSettAcctHistory expectedFimSettAcctHistory) {
        assertFimSettAcctHistoryAllUpdatablePropertiesEquals(
            expectedFimSettAcctHistory,
            getPersistedFimSettAcctHistory(expectedFimSettAcctHistory)
        );
    }
}
