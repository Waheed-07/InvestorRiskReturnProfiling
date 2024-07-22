package com.scb.fimob.web.rest;

import static com.scb.fimob.domain.FimAccountsWqAsserts.*;
import static com.scb.fimob.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.fimob.IntegrationTest;
import com.scb.fimob.domain.FimAccountsWq;
import com.scb.fimob.repository.FimAccountsWqRepository;
import com.scb.fimob.service.dto.FimAccountsWqDTO;
import com.scb.fimob.service.mapper.FimAccountsWqMapper;
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
 * Integration tests for the {@link FimAccountsWqResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FimAccountsWqResourceIT {

    private static final String DEFAULT_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RELN_ID = "AAAAAAAAAA";
    private static final String UPDATED_RELN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RELN_TYPE = "AAAAA";
    private static final String UPDATED_RELN_TYPE = "BBBBB";

    private static final String DEFAULT_OPER_INST = "AAAAAAAAAA";
    private static final String UPDATED_OPER_INST = "BBBBBBBBBB";

    private static final String DEFAULT_IS_ACCT_NBR = "AAAAAAAAAA";
    private static final String UPDATED_IS_ACCT_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_BND_ACCT_NBR = "AAAAAAAAAA";
    private static final String UPDATED_BND_ACCT_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_CLOSING_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLOSING_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_SEGMENT = "AAAAAAAAAA";
    private static final String UPDATED_SUB_SEGMENT = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CTRY_CODE = "AAA";
    private static final String UPDATED_CTRY_CODE = "BBB";

    private static final String DEFAULT_ACCT_OWNERS = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_OWNERS = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/fim-accounts-wqs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FimAccountsWqRepository fimAccountsWqRepository;

    @Autowired
    private FimAccountsWqMapper fimAccountsWqMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFimAccountsWqMockMvc;

    private FimAccountsWq fimAccountsWq;

    private FimAccountsWq insertedFimAccountsWq;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimAccountsWq createEntity(EntityManager em) {
        FimAccountsWq fimAccountsWq = new FimAccountsWq()
            .accountId(DEFAULT_ACCOUNT_ID)
            .custId(DEFAULT_CUST_ID)
            .relnId(DEFAULT_RELN_ID)
            .relnType(DEFAULT_RELN_TYPE)
            .operInst(DEFAULT_OPER_INST)
            .isAcctNbr(DEFAULT_IS_ACCT_NBR)
            .bndAcctNbr(DEFAULT_BND_ACCT_NBR)
            .closingId(DEFAULT_CLOSING_ID)
            .subSegment(DEFAULT_SUB_SEGMENT)
            .branchCode(DEFAULT_BRANCH_CODE)
            .acctStatus(DEFAULT_ACCT_STATUS)
            .ctryCode(DEFAULT_CTRY_CODE)
            .acctOwners(DEFAULT_ACCT_OWNERS)
            .remarks(DEFAULT_REMARKS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTs(DEFAULT_CREATED_TS)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedTs(DEFAULT_UPDATED_TS)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .uploadRemark(DEFAULT_UPLOAD_REMARK);
        return fimAccountsWq;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FimAccountsWq createUpdatedEntity(EntityManager em) {
        FimAccountsWq fimAccountsWq = new FimAccountsWq()
            .accountId(UPDATED_ACCOUNT_ID)
            .custId(UPDATED_CUST_ID)
            .relnId(UPDATED_RELN_ID)
            .relnType(UPDATED_RELN_TYPE)
            .operInst(UPDATED_OPER_INST)
            .isAcctNbr(UPDATED_IS_ACCT_NBR)
            .bndAcctNbr(UPDATED_BND_ACCT_NBR)
            .closingId(UPDATED_CLOSING_ID)
            .subSegment(UPDATED_SUB_SEGMENT)
            .branchCode(UPDATED_BRANCH_CODE)
            .acctStatus(UPDATED_ACCT_STATUS)
            .ctryCode(UPDATED_CTRY_CODE)
            .acctOwners(UPDATED_ACCT_OWNERS)
            .remarks(UPDATED_REMARKS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);
        return fimAccountsWq;
    }

    @BeforeEach
    public void initTest() {
        fimAccountsWq = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFimAccountsWq != null) {
            fimAccountsWqRepository.delete(insertedFimAccountsWq);
            insertedFimAccountsWq = null;
        }
    }

    @Test
    @Transactional
    void createFimAccountsWq() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FimAccountsWq
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);
        var returnedFimAccountsWqDTO = om.readValue(
            restFimAccountsWqMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimAccountsWqDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FimAccountsWqDTO.class
        );

        // Validate the FimAccountsWq in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFimAccountsWq = fimAccountsWqMapper.toEntity(returnedFimAccountsWqDTO);
        assertFimAccountsWqUpdatableFieldsEquals(returnedFimAccountsWq, getPersistedFimAccountsWq(returnedFimAccountsWq));

        insertedFimAccountsWq = returnedFimAccountsWq;
    }

    @Test
    @Transactional
    void createFimAccountsWqWithExistingId() throws Exception {
        // Create the FimAccountsWq with an existing ID
        fimAccountsWq.setId(1L);
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFimAccountsWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimAccountsWqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FimAccountsWq in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCustIdIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimAccountsWq.setCustId(null);

        // Create the FimAccountsWq, which fails.
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        restFimAccountsWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimAccountsWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRelnIdIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimAccountsWq.setRelnId(null);

        // Create the FimAccountsWq, which fails.
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        restFimAccountsWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimAccountsWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRelnTypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimAccountsWq.setRelnType(null);

        // Create the FimAccountsWq, which fails.
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        restFimAccountsWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimAccountsWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsAcctNbrIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimAccountsWq.setIsAcctNbr(null);

        // Create the FimAccountsWq, which fails.
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        restFimAccountsWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimAccountsWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBndAcctNbrIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimAccountsWq.setBndAcctNbr(null);

        // Create the FimAccountsWq, which fails.
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        restFimAccountsWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimAccountsWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAcctStatusIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fimAccountsWq.setAcctStatus(null);

        // Create the FimAccountsWq, which fails.
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        restFimAccountsWqMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimAccountsWqDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFimAccountsWqs() throws Exception {
        // Initialize the database
        insertedFimAccountsWq = fimAccountsWqRepository.saveAndFlush(fimAccountsWq);

        // Get all the fimAccountsWqList
        restFimAccountsWqMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fimAccountsWq.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountId").value(hasItem(DEFAULT_ACCOUNT_ID)))
            .andExpect(jsonPath("$.[*].custId").value(hasItem(DEFAULT_CUST_ID)))
            .andExpect(jsonPath("$.[*].relnId").value(hasItem(DEFAULT_RELN_ID)))
            .andExpect(jsonPath("$.[*].relnType").value(hasItem(DEFAULT_RELN_TYPE)))
            .andExpect(jsonPath("$.[*].operInst").value(hasItem(DEFAULT_OPER_INST)))
            .andExpect(jsonPath("$.[*].isAcctNbr").value(hasItem(DEFAULT_IS_ACCT_NBR)))
            .andExpect(jsonPath("$.[*].bndAcctNbr").value(hasItem(DEFAULT_BND_ACCT_NBR)))
            .andExpect(jsonPath("$.[*].closingId").value(hasItem(DEFAULT_CLOSING_ID)))
            .andExpect(jsonPath("$.[*].subSegment").value(hasItem(DEFAULT_SUB_SEGMENT)))
            .andExpect(jsonPath("$.[*].branchCode").value(hasItem(DEFAULT_BRANCH_CODE)))
            .andExpect(jsonPath("$.[*].acctStatus").value(hasItem(DEFAULT_ACCT_STATUS)))
            .andExpect(jsonPath("$.[*].ctryCode").value(hasItem(DEFAULT_CTRY_CODE)))
            .andExpect(jsonPath("$.[*].acctOwners").value(hasItem(DEFAULT_ACCT_OWNERS)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTs").value(hasItem(DEFAULT_CREATED_TS.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedTs").value(hasItem(DEFAULT_UPDATED_TS.toString())))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].uploadRemark").value(hasItem(DEFAULT_UPLOAD_REMARK)));
    }

    @Test
    @Transactional
    void getFimAccountsWq() throws Exception {
        // Initialize the database
        insertedFimAccountsWq = fimAccountsWqRepository.saveAndFlush(fimAccountsWq);

        // Get the fimAccountsWq
        restFimAccountsWqMockMvc
            .perform(get(ENTITY_API_URL_ID, fimAccountsWq.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fimAccountsWq.getId().intValue()))
            .andExpect(jsonPath("$.accountId").value(DEFAULT_ACCOUNT_ID))
            .andExpect(jsonPath("$.custId").value(DEFAULT_CUST_ID))
            .andExpect(jsonPath("$.relnId").value(DEFAULT_RELN_ID))
            .andExpect(jsonPath("$.relnType").value(DEFAULT_RELN_TYPE))
            .andExpect(jsonPath("$.operInst").value(DEFAULT_OPER_INST))
            .andExpect(jsonPath("$.isAcctNbr").value(DEFAULT_IS_ACCT_NBR))
            .andExpect(jsonPath("$.bndAcctNbr").value(DEFAULT_BND_ACCT_NBR))
            .andExpect(jsonPath("$.closingId").value(DEFAULT_CLOSING_ID))
            .andExpect(jsonPath("$.subSegment").value(DEFAULT_SUB_SEGMENT))
            .andExpect(jsonPath("$.branchCode").value(DEFAULT_BRANCH_CODE))
            .andExpect(jsonPath("$.acctStatus").value(DEFAULT_ACCT_STATUS))
            .andExpect(jsonPath("$.ctryCode").value(DEFAULT_CTRY_CODE))
            .andExpect(jsonPath("$.acctOwners").value(DEFAULT_ACCT_OWNERS))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTs").value(DEFAULT_CREATED_TS.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedTs").value(DEFAULT_UPDATED_TS.toString()))
            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS))
            .andExpect(jsonPath("$.uploadRemark").value(DEFAULT_UPLOAD_REMARK));
    }

    @Test
    @Transactional
    void getNonExistingFimAccountsWq() throws Exception {
        // Get the fimAccountsWq
        restFimAccountsWqMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFimAccountsWq() throws Exception {
        // Initialize the database
        insertedFimAccountsWq = fimAccountsWqRepository.saveAndFlush(fimAccountsWq);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimAccountsWq
        FimAccountsWq updatedFimAccountsWq = fimAccountsWqRepository.findById(fimAccountsWq.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFimAccountsWq are not directly saved in db
        em.detach(updatedFimAccountsWq);
        updatedFimAccountsWq
            .accountId(UPDATED_ACCOUNT_ID)
            .custId(UPDATED_CUST_ID)
            .relnId(UPDATED_RELN_ID)
            .relnType(UPDATED_RELN_TYPE)
            .operInst(UPDATED_OPER_INST)
            .isAcctNbr(UPDATED_IS_ACCT_NBR)
            .bndAcctNbr(UPDATED_BND_ACCT_NBR)
            .closingId(UPDATED_CLOSING_ID)
            .subSegment(UPDATED_SUB_SEGMENT)
            .branchCode(UPDATED_BRANCH_CODE)
            .acctStatus(UPDATED_ACCT_STATUS)
            .ctryCode(UPDATED_CTRY_CODE)
            .acctOwners(UPDATED_ACCT_OWNERS)
            .remarks(UPDATED_REMARKS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(updatedFimAccountsWq);

        restFimAccountsWqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimAccountsWqDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimAccountsWqDTO))
            )
            .andExpect(status().isOk());

        // Validate the FimAccountsWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFimAccountsWqToMatchAllProperties(updatedFimAccountsWq);
    }

    @Test
    @Transactional
    void putNonExistingFimAccountsWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimAccountsWq.setId(longCount.incrementAndGet());

        // Create the FimAccountsWq
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimAccountsWqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fimAccountsWqDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimAccountsWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimAccountsWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFimAccountsWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimAccountsWq.setId(longCount.incrementAndGet());

        // Create the FimAccountsWq
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimAccountsWqMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fimAccountsWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimAccountsWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFimAccountsWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimAccountsWq.setId(longCount.incrementAndGet());

        // Create the FimAccountsWq
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimAccountsWqMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fimAccountsWqDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimAccountsWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFimAccountsWqWithPatch() throws Exception {
        // Initialize the database
        insertedFimAccountsWq = fimAccountsWqRepository.saveAndFlush(fimAccountsWq);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimAccountsWq using partial update
        FimAccountsWq partialUpdatedFimAccountsWq = new FimAccountsWq();
        partialUpdatedFimAccountsWq.setId(fimAccountsWq.getId());

        partialUpdatedFimAccountsWq
            .relnId(UPDATED_RELN_ID)
            .relnType(UPDATED_RELN_TYPE)
            .closingId(UPDATED_CLOSING_ID)
            .ctryCode(UPDATED_CTRY_CODE)
            .acctOwners(UPDATED_ACCT_OWNERS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY);

        restFimAccountsWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimAccountsWq.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimAccountsWq))
            )
            .andExpect(status().isOk());

        // Validate the FimAccountsWq in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimAccountsWqUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFimAccountsWq, fimAccountsWq),
            getPersistedFimAccountsWq(fimAccountsWq)
        );
    }

    @Test
    @Transactional
    void fullUpdateFimAccountsWqWithPatch() throws Exception {
        // Initialize the database
        insertedFimAccountsWq = fimAccountsWqRepository.saveAndFlush(fimAccountsWq);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fimAccountsWq using partial update
        FimAccountsWq partialUpdatedFimAccountsWq = new FimAccountsWq();
        partialUpdatedFimAccountsWq.setId(fimAccountsWq.getId());

        partialUpdatedFimAccountsWq
            .accountId(UPDATED_ACCOUNT_ID)
            .custId(UPDATED_CUST_ID)
            .relnId(UPDATED_RELN_ID)
            .relnType(UPDATED_RELN_TYPE)
            .operInst(UPDATED_OPER_INST)
            .isAcctNbr(UPDATED_IS_ACCT_NBR)
            .bndAcctNbr(UPDATED_BND_ACCT_NBR)
            .closingId(UPDATED_CLOSING_ID)
            .subSegment(UPDATED_SUB_SEGMENT)
            .branchCode(UPDATED_BRANCH_CODE)
            .acctStatus(UPDATED_ACCT_STATUS)
            .ctryCode(UPDATED_CTRY_CODE)
            .acctOwners(UPDATED_ACCT_OWNERS)
            .remarks(UPDATED_REMARKS)
            .createdBy(UPDATED_CREATED_BY)
            .createdTs(UPDATED_CREATED_TS)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedTs(UPDATED_UPDATED_TS)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadRemark(UPDATED_UPLOAD_REMARK);

        restFimAccountsWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFimAccountsWq.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFimAccountsWq))
            )
            .andExpect(status().isOk());

        // Validate the FimAccountsWq in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFimAccountsWqUpdatableFieldsEquals(partialUpdatedFimAccountsWq, getPersistedFimAccountsWq(partialUpdatedFimAccountsWq));
    }

    @Test
    @Transactional
    void patchNonExistingFimAccountsWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimAccountsWq.setId(longCount.incrementAndGet());

        // Create the FimAccountsWq
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFimAccountsWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fimAccountsWqDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimAccountsWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimAccountsWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFimAccountsWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimAccountsWq.setId(longCount.incrementAndGet());

        // Create the FimAccountsWq
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimAccountsWqMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fimAccountsWqDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FimAccountsWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFimAccountsWq() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fimAccountsWq.setId(longCount.incrementAndGet());

        // Create the FimAccountsWq
        FimAccountsWqDTO fimAccountsWqDTO = fimAccountsWqMapper.toDto(fimAccountsWq);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFimAccountsWqMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fimAccountsWqDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FimAccountsWq in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFimAccountsWq() throws Exception {
        // Initialize the database
        insertedFimAccountsWq = fimAccountsWqRepository.saveAndFlush(fimAccountsWq);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fimAccountsWq
        restFimAccountsWqMockMvc
            .perform(delete(ENTITY_API_URL_ID, fimAccountsWq.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fimAccountsWqRepository.count();
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

    protected FimAccountsWq getPersistedFimAccountsWq(FimAccountsWq fimAccountsWq) {
        return fimAccountsWqRepository.findById(fimAccountsWq.getId()).orElseThrow();
    }

    protected void assertPersistedFimAccountsWqToMatchAllProperties(FimAccountsWq expectedFimAccountsWq) {
        assertFimAccountsWqAllPropertiesEquals(expectedFimAccountsWq, getPersistedFimAccountsWq(expectedFimAccountsWq));
    }

    protected void assertPersistedFimAccountsWqToMatchUpdatableProperties(FimAccountsWq expectedFimAccountsWq) {
        assertFimAccountsWqAllUpdatablePropertiesEquals(expectedFimAccountsWq, getPersistedFimAccountsWq(expectedFimAccountsWq));
    }
}
