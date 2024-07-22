package com.scb.fimob.web.rest;

import static com.scb.fimob.domain.FimCustWqAsserts.*;
import static com.scb.fimob.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.fimob.IntegrationTest;
import com.scb.fimob.domain.FimCustWq;
import com.scb.fimob.repository.FimCustWqRepository;
import com.scb.fimob.service.dto.FimCustWqDTO;
import com.scb.fimob.service.mapper.FimCustWqMapper;
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
 * Integration tests for the {@link FimCustWqResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FimCustWqResourceIT {

    private static final String DEFAULT_CUST_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ID = "BBBBBBBBBB";

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

    private static final String DEFAULT_UPLOAD_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_UPLOAD_REMARK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fim-cust-wqs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FimCustWqRepository fimCustWqRepository;

    @Autowired
    private FimCustWqMapper fimCustWqMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFimCustWqMockMvc;

    private FimCustWq fimCustWq;

    private FimCustWq insertedFimCustWq;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimCustWq createEntity(EntityManager em) {
        FimCustWq fimCustWq = new FimCustWq()
            .custId(DEFAULT_CUST_ID)
            .clientId(DEFAULT_CLIENT_ID)
            .idType(DEFAULT_ID_TYPE)
            .ctryCode(DEFAULT_CTRY_CODE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTs(DEFAULT_CREATED_TS)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedTs(DEFAULT_UPDATED_TS)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .uploadRemark(DEFAULT_UPLOAD_REMARK);
        return fimCustWq;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimCustWq createUpdatedEntity(EntityManager em) {
        FimCustWq fimCustWq = new FimCustWq()
            .custId(UPDATED_CUST_ID)
            .clientId(UPDATED_CLIENT_ID)
            .idType(UPDATED_ID_TYPE)
            .ctryCode(UPDATED_CTRY_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);
        return fimCustWq;
    }

    @BeforeEach
    public void initTest() {
        fimCustWq = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFimCustWq != null) {
            fimCustWqRepository.delete(insertedFimCustWq);
            insertedFimCustWq = null;
        }
    }

    @Test
    @Transactional
    void createFimCustWq() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FimCustWq
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);
        var returnedFimCustWqDTO = om.readValue(
            restFimCustWqMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustWqDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FimCustWqDTO.class
        );

        // Validate the FimCustWq in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFimCustWq = fimCustWqMapper.toEntity(returnedFimCustWqDTO);
        assertFimCustWqUpdatableFieldsEquals(returnedFimCustWq, getPersistedFimCustWq(returnedFimCustWq));

        insertedFimCustWq = returnedFimCustWq;
    }

    @Test
    @Transactional
    void createFimCustWqWithExistingId() throws Exception {
        // Create the FimCustWq with an existing ID
        fimCustWq.setId(1L);
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFimCustWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustWqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FimCustWq in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkClientIdIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCustWq.setClientId(null);

        // Create the FimCustWq, which fails.
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        restFimCustWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIdTypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCustWq.setIdType(null);

        // Create the FimCustWq, which fails.
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        restFimCustWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCtryCodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCustWq.setCtryCode(null);

        // Create the FimCustWq, which fails.
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        restFimCustWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCustWq.setCreatedBy(null);

        // Create the FimCustWq, which fails.
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        restFimCustWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFimCustWqs() throws Exception {
        // Initialize the database
        insertedFimCustWq = fimCustWqRepository.saveAndFlush(fimCustWq);

        // Get all the fimCustWqList
        restFimCustWqMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fimCustWq.getId().intValue())))
            .andExpect(jsonPath("$.[*].custId").value(hasItem(DEFAULT_CUST_ID)))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].idType").value(hasItem(DEFAULT_ID_TYPE)))
            .andExpect(jsonPath("$.[*].ctryCode").value(hasItem(DEFAULT_CTRY_CODE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTs").value(hasItem(DEFAULT_CREATED_TS.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedTs").value(hasItem(DEFAULT_UPDATED_TS.toString())))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].uploadRemark").value(hasItem(DEFAULT_UPLOAD_REMARK)));
    }

    @Test
    @Transactional
    void getFimCustWq() throws Exception {
        // Initialize the database
        insertedFimCustWq = fimCustWqRepository.saveAndFlush(fimCustWq);

        // Get the fimCustWq
        restFimCustWqMockMvc
            .perform(get(ENTITY_API_URL_ID, fimCustWq.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fimCustWq.getId().intValue()))
            .andExpect(jsonPath("$.custId").value(DEFAULT_CUST_ID))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID))
            .andExpect(jsonPath("$.idType").value(DEFAULT_ID_TYPE))
            .andExpect(jsonPath("$.ctryCode").value(DEFAULT_CTRY_CODE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTs").value(DEFAULT_CREATED_TS.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedTs").value(DEFAULT_UPDATED_TS.toString()))
            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS))
            .andExpect(jsonPath("$.uploadRemark").value(DEFAULT_UPLOAD_REMARK));
    }

    @Test
    @Transactional
    void getNonExistingFimCustWq() throws Exception {
        // Get the fimCustWq
        restFimCustWqMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFimCustWq() throws Exception {
        // Initialize the database
        insertedFimCustWq = fimCustWqRepository.saveAndFlush(fimCustWq);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimCustWq
        FimCustWq updatedFimCustWq = fimCustWqRepository.findById(fimCustWq.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFimCustWq are not directly saved in db
        em.detach(updatedFimCustWq);
        updatedFimCustWq
            .custId(UPDATED_CUST_ID)
            .clientId(UPDATED_CLIENT_ID)
            .idType(UPDATED_ID_TYPE)
            .ctryCode(UPDATED_CTRY_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(updatedFimCustWq);

        restFimCustWqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimCustWqDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimCustWqDTO))
            )
            .andExpect(status().isOk());

        // Validate the FimCustWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFimCustWqToMatchAllProperties(updatedFimCustWq);
    }

    @Test
    @Transactional
    void putNonExistingFimCustWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustWq.setId(longCount.incrementAndGet());

        // Create the FimCustWq
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimCustWqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimCustWqDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimCustWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCustWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFimCustWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustWq.setId(longCount.incrementAndGet());

        // Create the FimCustWq
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustWqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimCustWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCustWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFimCustWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustWq.setId(longCount.incrementAndGet());

        // Create the FimCustWq
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustWqMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustWqDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimCustWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFimCustWqWithPatch() throws Exception {
        // Initialize the database
        insertedFimCustWq = fimCustWqRepository.saveAndFlush(fimCustWq);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimCustWq using partial update
        FimCustWq partialUpdatedFimCustWq = new FimCustWq();
        partialUpdatedFimCustWq.setId(fimCustWq.getId());

        partialUpdatedFimCustWq.updatedBy(UPDATED_UPDATED_BY).uploadRemark(UPDATED_UPLOAD_REMARK);

        restFimCustWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimCustWq.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimCustWq))
            )
            .andExpect(status().isOk());

        // Validate the FimCustWq in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimCustWqUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFimCustWq, fimCustWq),
            getPersistedFimCustWq(fimCustWq)
        );
    }

    @Test
    @Transactional
    void fullUpdateFimCustWqWithPatch() throws Exception {
        // Initialize the database
        insertedFimCustWq = fimCustWqRepository.saveAndFlush(fimCustWq);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimCustWq using partial update
        FimCustWq partialUpdatedFimCustWq = new FimCustWq();
        partialUpdatedFimCustWq.setId(fimCustWq.getId());

        partialUpdatedFimCustWq
            .custId(UPDATED_CUST_ID)
            .clientId(UPDATED_CLIENT_ID)
            .idType(UPDATED_ID_TYPE)
            .ctryCode(UPDATED_CTRY_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);

        restFimCustWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimCustWq.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimCustWq))
            )
            .andExpect(status().isOk());

        // Validate the FimCustWq in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimCustWqUpdatableFieldsEquals(partialUpdatedFimCustWq, getPersistedFimCustWq(partialUpdatedFimCustWq));
    }

    @Test
    @Transactional
    void patchNonExistingFimCustWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustWq.setId(longCount.incrementAndGet());

        // Create the FimCustWq
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimCustWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fimCustWqDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimCustWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCustWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFimCustWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustWq.setId(longCount.incrementAndGet());

        // Create the FimCustWq
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimCustWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCustWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFimCustWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCustWq.setId(longCount.incrementAndGet());

        // Create the FimCustWq
        FimCustWqDTO fimCustWqDTO = fimCustWqMapper.toDto(fimCustWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustWqMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fimCustWqDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimCustWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFimCustWq() throws Exception {
        // Initialize the database
        insertedFimCustWq = fimCustWqRepository.saveAndFlush(fimCustWq);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fimCustWq
        restFimCustWqMockMvc
            .perform(delete(ENTITY_API_URL_ID, fimCustWq.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fimCustWqRepository.count();
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

    protected FimCustWq getPersistedFimCustWq(FimCustWq fimCustWq) {
        return fimCustWqRepository.findById(fimCustWq.getId()).orElseThrow();
    }

    protected void assertPersistedFimCustWqToMatchAllProperties(FimCustWq expectedFimCustWq) {
        assertFimCustWqAllPropertiesEquals(expectedFimCustWq, getPersistedFimCustWq(expectedFimCustWq));
    }

    protected void assertPersistedFimCustWqToMatchUpdatableProperties(FimCustWq expectedFimCustWq) {
        assertFimCustWqAllUpdatablePropertiesEquals(expectedFimCustWq, getPersistedFimCustWq(expectedFimCustWq));
    }
}
