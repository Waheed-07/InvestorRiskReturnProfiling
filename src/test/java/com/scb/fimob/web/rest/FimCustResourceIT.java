package com.scb.fimob.web.rest;

import static com.scb.fimob.domain.FimCustAsserts.*;
import static com.scb.fimob.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.fimob.IntegrationTest;
import com.scb.fimob.domain.FimCust;
import com.scb.fimob.repository.FimCustRepository;
import com.scb.fimob.service.dto.FimCustDTO;
import com.scb.fimob.service.mapper.FimCustMapper;
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
 * Integration tests for the {@link FimCustResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FimCustResourceIT {

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

    private static final String ENTITY_API_URL = "/api/fim-custs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FimCustRepository fimCustRepository;

    @Autowired
    private FimCustMapper fimCustMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFimCustMockMvc;

    private FimCust fimCust;

    private FimCust insertedFimCust;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimCust createEntity(EntityManager em) {
        FimCust fimCust = new FimCust()
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
        return fimCust;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimCust createUpdatedEntity(EntityManager em) {
        FimCust fimCust = new FimCust()
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
        return fimCust;
    }

    @BeforeEach
    public void initTest() {
        fimCust = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFimCust != null) {
            fimCustRepository.delete(insertedFimCust);
            insertedFimCust = null;
        }
    }

    @Test
    @Transactional
    void createFimCust() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FimCust
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);
        var returnedFimCustDTO = om.readValue(
            restFimCustMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FimCustDTO.class
        );

        // Validate the FimCust in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFimCust = fimCustMapper.toEntity(returnedFimCustDTO);
        assertFimCustUpdatableFieldsEquals(returnedFimCust, getPersistedFimCust(returnedFimCust));

        insertedFimCust = returnedFimCust;
    }

    @Test
    @Transactional
    void createFimCustWithExistingId() throws Exception {
        // Create the FimCust with an existing ID
        fimCust.setId(1L);
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFimCustMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FimCust in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkClientIdIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCust.setClientId(null);

        // Create the FimCust, which fails.
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        restFimCustMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIdTypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCust.setIdType(null);

        // Create the FimCust, which fails.
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        restFimCustMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCtryCodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCust.setCtryCode(null);

        // Create the FimCust, which fails.
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        restFimCustMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimCust.setCreatedBy(null);

        // Create the FimCust, which fails.
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        restFimCustMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFimCusts() throws Exception {
        // Initialize the database
        insertedFimCust = fimCustRepository.saveAndFlush(fimCust);

        // Get all the fimCustList
        restFimCustMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fimCust.getId().intValue())))
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
    void getFimCust() throws Exception {
        // Initialize the database
        insertedFimCust = fimCustRepository.saveAndFlush(fimCust);

        // Get the fimCust
        restFimCustMockMvc
            .perform(get(ENTITY_API_URL_ID, fimCust.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fimCust.getId().intValue()))
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
    void getNonExistingFimCust() throws Exception {
        // Get the fimCust
        restFimCustMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFimCust() throws Exception {
        // Initialize the database
        insertedFimCust = fimCustRepository.saveAndFlush(fimCust);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimCust
        FimCust updatedFimCust = fimCustRepository.findById(fimCust.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFimCust are not directly saved in db
        em.detach(updatedFimCust);
        updatedFimCust
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
        FimCustDTO fimCustDTO = fimCustMapper.toDto(updatedFimCust);

        restFimCustMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimCustDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustDTO))
            )
            .andExpect(status().isOk());

        // Validate the FimCust in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFimCustToMatchAllProperties(updatedFimCust);
    }

    @Test
    @Transactional
    void putNonExistingFimCust() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCust.setId(longCount.incrementAndGet());

        // Create the FimCust
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimCustMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimCustDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCust in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFimCust() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCust.setId(longCount.incrementAndGet());

        // Create the FimCust
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimCustDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCust in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFimCust() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCust.setId(longCount.incrementAndGet());

        // Create the FimCust
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimCustDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimCust in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFimCustWithPatch() throws Exception {
        // Initialize the database
        insertedFimCust = fimCustRepository.saveAndFlush(fimCust);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimCust using partial update
        FimCust partialUpdatedFimCust = new FimCust();
        partialUpdatedFimCust.setId(fimCust.getId());

        partialUpdatedFimCust
            .ctryCode(UPDATED_CTRY_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);

        restFimCustMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimCust.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimCust))
            )
            .andExpect(status().isOk());

        // Validate the FimCust in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimCustUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedFimCust, fimCust), getPersistedFimCust(fimCust));
    }

    @Test
    @Transactional
    void fullUpdateFimCustWithPatch() throws Exception {
        // Initialize the database
        insertedFimCust = fimCustRepository.saveAndFlush(fimCust);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimCust using partial update
        FimCust partialUpdatedFimCust = new FimCust();
        partialUpdatedFimCust.setId(fimCust.getId());

        partialUpdatedFimCust
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

        restFimCustMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimCust.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimCust))
            )
            .andExpect(status().isOk());

        // Validate the FimCust in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimCustUpdatableFieldsEquals(partialUpdatedFimCust, getPersistedFimCust(partialUpdatedFimCust));
    }

    @Test
    @Transactional
    void patchNonExistingFimCust() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCust.setId(longCount.incrementAndGet());

        // Create the FimCust
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimCustMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fimCustDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimCustDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCust in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFimCust() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCust.setId(longCount.incrementAndGet());

        // Create the FimCust
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimCustDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimCust in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFimCust() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimCust.setId(longCount.incrementAndGet());

        // Create the FimCust
        FimCustDTO fimCustDTO = fimCustMapper.toDto(fimCust);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimCustMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fimCustDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimCust in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFimCust() throws Exception {
        // Initialize the database
        insertedFimCust = fimCustRepository.saveAndFlush(fimCust);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fimCust
        restFimCustMockMvc
            .perform(delete(ENTITY_API_URL_ID, fimCust.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fimCustRepository.count();
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

    protected FimCust getPersistedFimCust(FimCust fimCust) {
        return fimCustRepository.findById(fimCust.getId()).orElseThrow();
    }

    protected void assertPersistedFimCustToMatchAllProperties(FimCust expectedFimCust) {
        assertFimCustAllPropertiesEquals(expectedFimCust, getPersistedFimCust(expectedFimCust));
    }

    protected void assertPersistedFimCustToMatchUpdatableProperties(FimCust expectedFimCust) {
        assertFimCustAllUpdatablePropertiesEquals(expectedFimCust, getPersistedFimCust(expectedFimCust));
    }
}
