package com.simplerishta.cms.web.rest;

import static com.simplerishta.cms.domain.ProfileVerificationStatusAsserts.*;
import static com.simplerishta.cms.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplerishta.cms.IntegrationTest;
import com.simplerishta.cms.domain.ProfileVerificationStatus;
import com.simplerishta.cms.repository.ProfileVerificationStatusRepository;
import com.simplerishta.cms.service.dto.ProfileVerificationStatusDTO;
import com.simplerishta.cms.service.mapper.ProfileVerificationStatusMapper;
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
 * Integration tests for the {@link ProfileVerificationStatusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProfileVerificationStatusResourceIT {

    private static final String DEFAULT_ATTRIBUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_VERIFIED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VERIFIED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VERIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_VERIFIED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/profile-verification-statuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProfileVerificationStatusRepository profileVerificationStatusRepository;

    @Autowired
    private ProfileVerificationStatusMapper profileVerificationStatusMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProfileVerificationStatusMockMvc;

    private ProfileVerificationStatus profileVerificationStatus;

    private ProfileVerificationStatus insertedProfileVerificationStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfileVerificationStatus createEntity() {
        return new ProfileVerificationStatus()
            .attributeName(DEFAULT_ATTRIBUTE_NAME)
            .status(DEFAULT_STATUS)
            .verifiedAt(DEFAULT_VERIFIED_AT)
            .verifiedBy(DEFAULT_VERIFIED_BY);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfileVerificationStatus createUpdatedEntity() {
        return new ProfileVerificationStatus()
            .attributeName(UPDATED_ATTRIBUTE_NAME)
            .status(UPDATED_STATUS)
            .verifiedAt(UPDATED_VERIFIED_AT)
            .verifiedBy(UPDATED_VERIFIED_BY);
    }

    @BeforeEach
    public void initTest() {
        profileVerificationStatus = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedProfileVerificationStatus != null) {
            profileVerificationStatusRepository.delete(insertedProfileVerificationStatus);
            insertedProfileVerificationStatus = null;
        }
    }

    @Test
    @Transactional
    void createProfileVerificationStatus() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProfileVerificationStatus
        ProfileVerificationStatusDTO profileVerificationStatusDTO = profileVerificationStatusMapper.toDto(profileVerificationStatus);
        var returnedProfileVerificationStatusDTO = om.readValue(
            restProfileVerificationStatusMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(profileVerificationStatusDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProfileVerificationStatusDTO.class
        );

        // Validate the ProfileVerificationStatus in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedProfileVerificationStatus = profileVerificationStatusMapper.toEntity(returnedProfileVerificationStatusDTO);
        assertProfileVerificationStatusUpdatableFieldsEquals(
            returnedProfileVerificationStatus,
            getPersistedProfileVerificationStatus(returnedProfileVerificationStatus)
        );

        insertedProfileVerificationStatus = returnedProfileVerificationStatus;
    }

    @Test
    @Transactional
    void createProfileVerificationStatusWithExistingId() throws Exception {
        // Create the ProfileVerificationStatus with an existing ID
        profileVerificationStatus.setId(1L);
        ProfileVerificationStatusDTO profileVerificationStatusDTO = profileVerificationStatusMapper.toDto(profileVerificationStatus);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfileVerificationStatusMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(profileVerificationStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProfileVerificationStatus in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProfileVerificationStatuses() throws Exception {
        // Initialize the database
        insertedProfileVerificationStatus = profileVerificationStatusRepository.saveAndFlush(profileVerificationStatus);

        // Get all the profileVerificationStatusList
        restProfileVerificationStatusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profileVerificationStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].attributeName").value(hasItem(DEFAULT_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].verifiedAt").value(hasItem(DEFAULT_VERIFIED_AT.toString())))
            .andExpect(jsonPath("$.[*].verifiedBy").value(hasItem(DEFAULT_VERIFIED_BY)));
    }

    @Test
    @Transactional
    void getProfileVerificationStatus() throws Exception {
        // Initialize the database
        insertedProfileVerificationStatus = profileVerificationStatusRepository.saveAndFlush(profileVerificationStatus);

        // Get the profileVerificationStatus
        restProfileVerificationStatusMockMvc
            .perform(get(ENTITY_API_URL_ID, profileVerificationStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(profileVerificationStatus.getId().intValue()))
            .andExpect(jsonPath("$.attributeName").value(DEFAULT_ATTRIBUTE_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.verifiedAt").value(DEFAULT_VERIFIED_AT.toString()))
            .andExpect(jsonPath("$.verifiedBy").value(DEFAULT_VERIFIED_BY));
    }

    @Test
    @Transactional
    void getNonExistingProfileVerificationStatus() throws Exception {
        // Get the profileVerificationStatus
        restProfileVerificationStatusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProfileVerificationStatus() throws Exception {
        // Initialize the database
        insertedProfileVerificationStatus = profileVerificationStatusRepository.saveAndFlush(profileVerificationStatus);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the profileVerificationStatus
        ProfileVerificationStatus updatedProfileVerificationStatus = profileVerificationStatusRepository
            .findById(profileVerificationStatus.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedProfileVerificationStatus are not directly saved in db
        em.detach(updatedProfileVerificationStatus);
        updatedProfileVerificationStatus
            .attributeName(UPDATED_ATTRIBUTE_NAME)
            .status(UPDATED_STATUS)
            .verifiedAt(UPDATED_VERIFIED_AT)
            .verifiedBy(UPDATED_VERIFIED_BY);
        ProfileVerificationStatusDTO profileVerificationStatusDTO = profileVerificationStatusMapper.toDto(updatedProfileVerificationStatus);

        restProfileVerificationStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, profileVerificationStatusDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(profileVerificationStatusDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProfileVerificationStatus in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProfileVerificationStatusToMatchAllProperties(updatedProfileVerificationStatus);
    }

    @Test
    @Transactional
    void putNonExistingProfileVerificationStatus() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        profileVerificationStatus.setId(longCount.incrementAndGet());

        // Create the ProfileVerificationStatus
        ProfileVerificationStatusDTO profileVerificationStatusDTO = profileVerificationStatusMapper.toDto(profileVerificationStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfileVerificationStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, profileVerificationStatusDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(profileVerificationStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProfileVerificationStatus in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProfileVerificationStatus() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        profileVerificationStatus.setId(longCount.incrementAndGet());

        // Create the ProfileVerificationStatus
        ProfileVerificationStatusDTO profileVerificationStatusDTO = profileVerificationStatusMapper.toDto(profileVerificationStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProfileVerificationStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(profileVerificationStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProfileVerificationStatus in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProfileVerificationStatus() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        profileVerificationStatus.setId(longCount.incrementAndGet());

        // Create the ProfileVerificationStatus
        ProfileVerificationStatusDTO profileVerificationStatusDTO = profileVerificationStatusMapper.toDto(profileVerificationStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProfileVerificationStatusMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(profileVerificationStatusDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProfileVerificationStatus in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProfileVerificationStatusWithPatch() throws Exception {
        // Initialize the database
        insertedProfileVerificationStatus = profileVerificationStatusRepository.saveAndFlush(profileVerificationStatus);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the profileVerificationStatus using partial update
        ProfileVerificationStatus partialUpdatedProfileVerificationStatus = new ProfileVerificationStatus();
        partialUpdatedProfileVerificationStatus.setId(profileVerificationStatus.getId());

        partialUpdatedProfileVerificationStatus.attributeName(UPDATED_ATTRIBUTE_NAME).verifiedAt(UPDATED_VERIFIED_AT);

        restProfileVerificationStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProfileVerificationStatus.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProfileVerificationStatus))
            )
            .andExpect(status().isOk());

        // Validate the ProfileVerificationStatus in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProfileVerificationStatusUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProfileVerificationStatus, profileVerificationStatus),
            getPersistedProfileVerificationStatus(profileVerificationStatus)
        );
    }

    @Test
    @Transactional
    void fullUpdateProfileVerificationStatusWithPatch() throws Exception {
        // Initialize the database
        insertedProfileVerificationStatus = profileVerificationStatusRepository.saveAndFlush(profileVerificationStatus);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the profileVerificationStatus using partial update
        ProfileVerificationStatus partialUpdatedProfileVerificationStatus = new ProfileVerificationStatus();
        partialUpdatedProfileVerificationStatus.setId(profileVerificationStatus.getId());

        partialUpdatedProfileVerificationStatus
            .attributeName(UPDATED_ATTRIBUTE_NAME)
            .status(UPDATED_STATUS)
            .verifiedAt(UPDATED_VERIFIED_AT)
            .verifiedBy(UPDATED_VERIFIED_BY);

        restProfileVerificationStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProfileVerificationStatus.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProfileVerificationStatus))
            )
            .andExpect(status().isOk());

        // Validate the ProfileVerificationStatus in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProfileVerificationStatusUpdatableFieldsEquals(
            partialUpdatedProfileVerificationStatus,
            getPersistedProfileVerificationStatus(partialUpdatedProfileVerificationStatus)
        );
    }

    @Test
    @Transactional
    void patchNonExistingProfileVerificationStatus() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        profileVerificationStatus.setId(longCount.incrementAndGet());

        // Create the ProfileVerificationStatus
        ProfileVerificationStatusDTO profileVerificationStatusDTO = profileVerificationStatusMapper.toDto(profileVerificationStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfileVerificationStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, profileVerificationStatusDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(profileVerificationStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProfileVerificationStatus in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProfileVerificationStatus() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        profileVerificationStatus.setId(longCount.incrementAndGet());

        // Create the ProfileVerificationStatus
        ProfileVerificationStatusDTO profileVerificationStatusDTO = profileVerificationStatusMapper.toDto(profileVerificationStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProfileVerificationStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(profileVerificationStatusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProfileVerificationStatus in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProfileVerificationStatus() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        profileVerificationStatus.setId(longCount.incrementAndGet());

        // Create the ProfileVerificationStatus
        ProfileVerificationStatusDTO profileVerificationStatusDTO = profileVerificationStatusMapper.toDto(profileVerificationStatus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProfileVerificationStatusMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(profileVerificationStatusDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProfileVerificationStatus in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProfileVerificationStatus() throws Exception {
        // Initialize the database
        insertedProfileVerificationStatus = profileVerificationStatusRepository.saveAndFlush(profileVerificationStatus);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the profileVerificationStatus
        restProfileVerificationStatusMockMvc
            .perform(delete(ENTITY_API_URL_ID, profileVerificationStatus.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return profileVerificationStatusRepository.count();
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

    protected ProfileVerificationStatus getPersistedProfileVerificationStatus(ProfileVerificationStatus profileVerificationStatus) {
        return profileVerificationStatusRepository.findById(profileVerificationStatus.getId()).orElseThrow();
    }

    protected void assertPersistedProfileVerificationStatusToMatchAllProperties(
        ProfileVerificationStatus expectedProfileVerificationStatus
    ) {
        assertProfileVerificationStatusAllPropertiesEquals(
            expectedProfileVerificationStatus,
            getPersistedProfileVerificationStatus(expectedProfileVerificationStatus)
        );
    }

    protected void assertPersistedProfileVerificationStatusToMatchUpdatableProperties(
        ProfileVerificationStatus expectedProfileVerificationStatus
    ) {
        assertProfileVerificationStatusAllUpdatablePropertiesEquals(
            expectedProfileVerificationStatus,
            getPersistedProfileVerificationStatus(expectedProfileVerificationStatus)
        );
    }
}
