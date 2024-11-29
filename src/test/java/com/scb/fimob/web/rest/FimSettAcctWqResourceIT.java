package com.scb.fimob.web.rest;

import static com.scb.fimob.domain.FimSettAcctWqAsserts.*;
import static com.scb.fimob.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.fimob.IntegrationTest;
import com.scb.fimob.domain.FimSettAcctWq;
import com.scb.fimob.repository.FimSettAcctWqRepository;
import com.scb.fimob.service.dto.FimSettAcctWqDTO;
import com.scb.fimob.service.mapper.FimSettAcctWqMapper;
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
 * Integration tests for the {@link FimSettAcctWqResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FimSettAcctWqResourceIT {

    private static final String DEFAULT_SETTACC_ID = "AAAAAAAAAA";
    private static final String UPDATED_SETTACC_ID = "BBBBBBBBBB";

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

    private static final String DEFAULT_UPLOAD_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_UPLOAD_REMARK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fim-sett-acct-wqs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FimSettAcctWqRepository fimSettAcctWqRepository;

    @Autowired
    private FimSettAcctWqMapper fimSettAcctWqMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFimSettAcctWqMockMvc;

    private FimSettAcctWq fimSettAcctWq;

    private FimSettAcctWq insertedFimSettAcctWq;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimSettAcctWq createEntity(EntityManager em) {
        FimSettAcctWq fimSettAcctWq = new FimSettAcctWq()
            .settaccId(DEFAULT_SETTACC_ID)
            .accountId(DEFAULT_ACCOUNT_ID)
            .settAcctNbr(DEFAULT_SETT_ACCT_NBR)
            .settCcy(DEFAULT_SETT_CCY)
            .settAcctStatus(DEFAULT_SETT_ACCT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTs(DEFAULT_CREATED_TS)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedTs(DEFAULT_UPDATED_TS)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .uploadRemark(DEFAULT_UPLOAD_REMARK);
        return fimSettAcctWq;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimSettAcctWq createUpdatedEntity(EntityManager em) {
        FimSettAcctWq fimSettAcctWq = new FimSettAcctWq()
            .settaccId(UPDATED_SETTACC_ID)
            .accountId(UPDATED_ACCOUNT_ID)
            .settAcctNbr(UPDATED_SETT_ACCT_NBR)
            .settCcy(UPDATED_SETT_CCY)
            .settAcctStatus(UPDATED_SETT_ACCT_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);
        return fimSettAcctWq;
    }

    @BeforeEach
    public void initTest() {
        fimSettAcctWq = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFimSettAcctWq != null) {
            fimSettAcctWqRepository.delete(insertedFimSettAcctWq);
            insertedFimSettAcctWq = null;
        }
    }

    @Test
    @Transactional
    void createFimSettAcctWq() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FimSettAcctWq
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);
        var returnedFimSettAcctWqDTO = om.readValue(
            restFimSettAcctWqMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctWqDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FimSettAcctWqDTO.class
        );

        // Validate the FimSettAcctWq in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFimSettAcctWq = fimSettAcctWqMapper.toEntity(returnedFimSettAcctWqDTO);
        assertFimSettAcctWqUpdatableFieldsEquals(returnedFimSettAcctWq, getPersistedFimSettAcctWq(returnedFimSettAcctWq));

        insertedFimSettAcctWq = returnedFimSettAcctWq;
    }

    @Test
    @Transactional
    void createFimSettAcctWqWithExistingId() throws Exception {
        // Create the FimSettAcctWq with an existing ID
        fimSettAcctWq.setId(1L);
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFimSettAcctWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctWqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctWq in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAccountIdIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimSettAcctWq.setAccountId(null);

        // Create the FimSettAcctWq, which fails.
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        restFimSettAcctWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSettAcctNbrIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimSettAcctWq.setSettAcctNbr(null);

        // Create the FimSettAcctWq, which fails.
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        restFimSettAcctWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSettCcyIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimSettAcctWq.setSettCcy(null);

        // Create the FimSettAcctWq, which fails.
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        restFimSettAcctWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSettAcctStatusIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimSettAcctWq.setSettAcctStatus(null);

        // Create the FimSettAcctWq, which fails.
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        restFimSettAcctWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFimSettAcctWqs() throws Exception {
        // Initialize the database
        insertedFimSettAcctWq = fimSettAcctWqRepository.saveAndFlush(fimSettAcctWq);

        // Get all the fimSettAcctWqList
        restFimSettAcctWqMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fimSettAcctWq.getId().intValue())))
            .andExpect(jsonPath("$.[*].settaccId").value(hasItem(DEFAULT_SETTACC_ID)))
            .andExpect(jsonPath("$.[*].accountId").value(hasItem(DEFAULT_ACCOUNT_ID)))
            .andExpect(jsonPath("$.[*].settAcctNbr").value(hasItem(DEFAULT_SETT_ACCT_NBR)))
            .andExpect(jsonPath("$.[*].settCcy").value(hasItem(DEFAULT_SETT_CCY)))
            .andExpect(jsonPath("$.[*].settAcctStatus").value(hasItem(DEFAULT_SETT_ACCT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTs").value(hasItem(DEFAULT_CREATED_TS.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedTs").value(hasItem(DEFAULT_UPDATED_TS.toString())))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].uploadRemark").value(hasItem(DEFAULT_UPLOAD_REMARK)));
    }

    @Test
    @Transactional
    void getFimSettAcctWq() throws Exception {
        // Initialize the database
        insertedFimSettAcctWq = fimSettAcctWqRepository.saveAndFlush(fimSettAcctWq);

        // Get the fimSettAcctWq
        restFimSettAcctWqMockMvc
            .perform(get(ENTITY_API_URL_ID, fimSettAcctWq.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fimSettAcctWq.getId().intValue()))
            .andExpect(jsonPath("$.settaccId").value(DEFAULT_SETTACC_ID))
            .andExpect(jsonPath("$.accountId").value(DEFAULT_ACCOUNT_ID))
            .andExpect(jsonPath("$.settAcctNbr").value(DEFAULT_SETT_ACCT_NBR))
            .andExpect(jsonPath("$.settCcy").value(DEFAULT_SETT_CCY))
            .andExpect(jsonPath("$.settAcctStatus").value(DEFAULT_SETT_ACCT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTs").value(DEFAULT_CREATED_TS.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedTs").value(DEFAULT_UPDATED_TS.toString()))
            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS))
            .andExpect(jsonPath("$.uploadRemark").value(DEFAULT_UPLOAD_REMARK));
    }

    @Test
    @Transactional
    void getNonExistingFimSettAcctWq() throws Exception {
        // Get the fimSettAcctWq
        restFimSettAcctWqMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFimSettAcctWq() throws Exception {
        // Initialize the database
        insertedFimSettAcctWq = fimSettAcctWqRepository.saveAndFlush(fimSettAcctWq);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimSettAcctWq
        FimSettAcctWq updatedFimSettAcctWq = fimSettAcctWqRepository.findById(fimSettAcctWq.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFimSettAcctWq are not directly saved in db
        em.detach(updatedFimSettAcctWq);
        updatedFimSettAcctWq
            .settaccId(UPDATED_SETTACC_ID)
            .accountId(UPDATED_ACCOUNT_ID)
            .settAcctNbr(UPDATED_SETT_ACCT_NBR)
            .settCcy(UPDATED_SETT_CCY)
            .settAcctStatus(UPDATED_SETT_ACCT_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(updatedFimSettAcctWq);

        restFimSettAcctWqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimSettAcctWqDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimSettAcctWqDTO))
            )
            .andExpect(status().isOk());

        // Validate the FimSettAcctWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFimSettAcctWqToMatchAllProperties(updatedFimSettAcctWq);
    }

    @Test
    @Transactional
    void putNonExistingFimSettAcctWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctWq.setId(longCount.incrementAndGet());

        // Create the FimSettAcctWq
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimSettAcctWqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimSettAcctWqDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimSettAcctWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFimSettAcctWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctWq.setId(longCount.incrementAndGet());

        // Create the FimSettAcctWq
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimSettAcctWqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimSettAcctWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFimSettAcctWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctWq.setId(longCount.incrementAndGet());

        // Create the FimSettAcctWq
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimSettAcctWqMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimSettAcctWqDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimSettAcctWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFimSettAcctWqWithPatch() throws Exception {
        // Initialize the database
        insertedFimSettAcctWq = fimSettAcctWqRepository.saveAndFlush(fimSettAcctWq);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimSettAcctWq using partial update
        FimSettAcctWq partialUpdatedFimSettAcctWq = new FimSettAcctWq();
        partialUpdatedFimSettAcctWq.setId(fimSettAcctWq.getId());

        partialUpdatedFimSettAcctWq
            .accountId(UPDATED_ACCOUNT_ID)
            .settAcctNbr(UPDATED_SETT_ACCT_NBR)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS);

        restFimSettAcctWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimSettAcctWq.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimSettAcctWq))
            )
            .andExpect(status().isOk());

        // Validate the FimSettAcctWq in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimSettAcctWqUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFimSettAcctWq, fimSettAcctWq),
            getPersistedFimSettAcctWq(fimSettAcctWq)
        );
    }

    @Test
    @Transactional
    void fullUpdateFimSettAcctWqWithPatch() throws Exception {
        // Initialize the database
        insertedFimSettAcctWq = fimSettAcctWqRepository.saveAndFlush(fimSettAcctWq);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimSettAcctWq using partial update
        FimSettAcctWq partialUpdatedFimSettAcctWq = new FimSettAcctWq();
        partialUpdatedFimSettAcctWq.setId(fimSettAcctWq.getId());

        partialUpdatedFimSettAcctWq
            .settaccId(UPDATED_SETTACC_ID)
            .accountId(UPDATED_ACCOUNT_ID)
            .settAcctNbr(UPDATED_SETT_ACCT_NBR)
            .settCcy(UPDATED_SETT_CCY)
            .settAcctStatus(UPDATED_SETT_ACCT_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);

        restFimSettAcctWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimSettAcctWq.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimSettAcctWq))
            )
            .andExpect(status().isOk());

        // Validate the FimSettAcctWq in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimSettAcctWqUpdatableFieldsEquals(partialUpdatedFimSettAcctWq, getPersistedFimSettAcctWq(partialUpdatedFimSettAcctWq));
    }

    @Test
    @Transactional
    void patchNonExistingFimSettAcctWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctWq.setId(longCount.incrementAndGet());

        // Create the FimSettAcctWq
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimSettAcctWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fimSettAcctWqDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimSettAcctWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFimSettAcctWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctWq.setId(longCount.incrementAndGet());

        // Create the FimSettAcctWq
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimSettAcctWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimSettAcctWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimSettAcctWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFimSettAcctWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimSettAcctWq.setId(longCount.incrementAndGet());

        // Create the FimSettAcctWq
        FimSettAcctWqDTO fimSettAcctWqDTO = fimSettAcctWqMapper.toDto(fimSettAcctWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimSettAcctWqMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fimSettAcctWqDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimSettAcctWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFimSettAcctWq() throws Exception {
        // Initialize the database
        insertedFimSettAcctWq = fimSettAcctWqRepository.saveAndFlush(fimSettAcctWq);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fimSettAcctWq
        restFimSettAcctWqMockMvc
            .perform(delete(ENTITY_API_URL_ID, fimSettAcctWq.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fimSettAcctWqRepository.count();
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

    protected FimSettAcctWq getPersistedFimSettAcctWq(FimSettAcctWq fimSettAcctWq) {
        return fimSettAcctWqRepository.findById(fimSettAcctWq.getId()).orElseThrow();
    }

    protected void assertPersistedFimSettAcctWqToMatchAllProperties(FimSettAcctWq expectedFimSettAcctWq) {
        assertFimSettAcctWqAllPropertiesEquals(expectedFimSettAcctWq, getPersistedFimSettAcctWq(expectedFimSettAcctWq));
    }

    protected void assertPersistedFimSettAcctWqToMatchUpdatableProperties(FimSettAcctWq expectedFimSettAcctWq) {
        assertFimSettAcctWqAllUpdatablePropertiesEquals(expectedFimSettAcctWq, getPersistedFimSettAcctWq(expectedFimSettAcctWq));
    }
}
