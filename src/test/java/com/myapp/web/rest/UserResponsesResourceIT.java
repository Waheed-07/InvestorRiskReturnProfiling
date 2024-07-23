package com.myapp.web.rest;

import static com.myapp.domain.UserResponsesAsserts.*;
import static com.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.IntegrationTest;
import com.myapp.domain.UserResponses;
import com.myapp.repository.UserResponsesRepository;
import com.myapp.service.dto.UserResponsesDTO;
import com.myapp.service.mapper.UserResponsesMapper;
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
 * Integration tests for the {@link UserResponsesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserResponsesResourceIT {

    private static final String DEFAULT_RESPONSE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_TEXT = "BBBBBBBBBB";

    private static final Instant DEFAULT_RESPONSE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RESPONSE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/user-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserResponsesRepository userResponsesRepository;

    @Autowired
    private UserResponsesMapper userResponsesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserResponsesMockMvc;

    private UserResponses userResponses;

    private UserResponses insertedUserResponses;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserResponses createEntity(EntityManager em) {
        UserResponses userResponses = new UserResponses().responseText(DEFAULT_RESPONSE_TEXT).responseDate(DEFAULT_RESPONSE_DATE);
        return userResponses;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserResponses createUpdatedEntity(EntityManager em) {
        UserResponses userResponses = new UserResponses().responseText(UPDATED_RESPONSE_TEXT).responseDate(UPDATED_RESPONSE_DATE);
        return userResponses;
    }

    @BeforeEach
    public void initTest() {
        userResponses = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedUserResponses != null) {
            userResponsesRepository.delete(insertedUserResponses);
            insertedUserResponses = null;
        }
    }

    @Test
    @Transactional
    void createUserResponses() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the UserResponses
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);
        var returnedUserResponsesDTO = om.readValue(
            restUserResponsesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userResponsesDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            UserResponsesDTO.class
        );

        // Validate the UserResponses in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedUserResponses = userResponsesMapper.toEntity(returnedUserResponsesDTO);
        assertUserResponsesUpdatableFieldsEquals(returnedUserResponses, getPersistedUserResponses(returnedUserResponses));

        insertedUserResponses = returnedUserResponses;
    }

    @Test
    @Transactional
    void createUserResponsesWithExistingId() throws Exception {
        // Create the UserResponses with an existing ID
        userResponses.setId(1L);
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserResponsesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userResponsesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserResponses in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkResponseTextIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userResponses.setResponseText(null);

        // Create the UserResponses, which fails.
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);

        restUserResponsesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userResponsesDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkResponseDateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userResponses.setResponseDate(null);

        // Create the UserResponses, which fails.
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);

        restUserResponsesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userResponsesDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllUserResponses() throws Exception {
        // Initialize the database
        insertedUserResponses = userResponsesRepository.saveAndFlush(userResponses);

        // Get all the userResponsesList
        restUserResponsesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userResponses.getId().intValue())))
            .andExpect(jsonPath("$.[*].responseText").value(hasItem(DEFAULT_RESPONSE_TEXT)))
            .andExpect(jsonPath("$.[*].responseDate").value(hasItem(DEFAULT_RESPONSE_DATE.toString())));
    }

    @Test
    @Transactional
    void getUserResponses() throws Exception {
        // Initialize the database
        insertedUserResponses = userResponsesRepository.saveAndFlush(userResponses);

        // Get the userResponses
        restUserResponsesMockMvc
            .perform(get(ENTITY_API_URL_ID, userResponses.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userResponses.getId().intValue()))
            .andExpect(jsonPath("$.responseText").value(DEFAULT_RESPONSE_TEXT))
            .andExpect(jsonPath("$.responseDate").value(DEFAULT_RESPONSE_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingUserResponses() throws Exception {
        // Get the userResponses
        restUserResponsesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUserResponses() throws Exception {
        // Initialize the database
        insertedUserResponses = userResponsesRepository.saveAndFlush(userResponses);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the userResponses
        UserResponses updatedUserResponses = userResponsesRepository.findById(userResponses.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedUserResponses are not directly saved in db
        em.detach(updatedUserResponses);
        updatedUserResponses.responseText(UPDATED_RESPONSE_TEXT).responseDate(UPDATED_RESPONSE_DATE);
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(updatedUserResponses);

        restUserResponsesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userResponsesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(userResponsesDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserResponses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedUserResponsesToMatchAllProperties(updatedUserResponses);
    }

    @Test
    @Transactional
    void putNonExistingUserResponses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userResponses.setId(longCount.incrementAndGet());

        // Create the UserResponses
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserResponsesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userResponsesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(userResponsesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserResponses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserResponses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userResponses.setId(longCount.incrementAndGet());

        // Create the UserResponses
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserResponsesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(userResponsesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserResponses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserResponses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userResponses.setId(longCount.incrementAndGet());

        // Create the UserResponses
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserResponsesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userResponsesDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserResponses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserResponsesWithPatch() throws Exception {
        // Initialize the database
        insertedUserResponses = userResponsesRepository.saveAndFlush(userResponses);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the userResponses using partial update
        UserResponses partialUpdatedUserResponses = new UserResponses();
        partialUpdatedUserResponses.setId(userResponses.getId());

        restUserResponsesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserResponses.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUserResponses))
            )
            .andExpect(status().isOk());

        // Validate the UserResponses in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUserResponsesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedUserResponses, userResponses),
            getPersistedUserResponses(userResponses)
        );
    }

    @Test
    @Transactional
    void fullUpdateUserResponsesWithPatch() throws Exception {
        // Initialize the database
        insertedUserResponses = userResponsesRepository.saveAndFlush(userResponses);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the userResponses using partial update
        UserResponses partialUpdatedUserResponses = new UserResponses();
        partialUpdatedUserResponses.setId(userResponses.getId());

        partialUpdatedUserResponses.responseText(UPDATED_RESPONSE_TEXT).responseDate(UPDATED_RESPONSE_DATE);

        restUserResponsesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserResponses.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUserResponses))
            )
            .andExpect(status().isOk());

        // Validate the UserResponses in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUserResponsesUpdatableFieldsEquals(partialUpdatedUserResponses, getPersistedUserResponses(partialUpdatedUserResponses));
    }

    @Test
    @Transactional
    void patchNonExistingUserResponses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userResponses.setId(longCount.incrementAndGet());

        // Create the UserResponses
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserResponsesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userResponsesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(userResponsesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserResponses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserResponses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userResponses.setId(longCount.incrementAndGet());

        // Create the UserResponses
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserResponsesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(userResponsesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserResponses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserResponses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userResponses.setId(longCount.incrementAndGet());

        // Create the UserResponses
        UserResponsesDTO userResponsesDTO = userResponsesMapper.toDto(userResponses);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserResponsesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(userResponsesDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserResponses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserResponses() throws Exception {
        // Initialize the database
        insertedUserResponses = userResponsesRepository.saveAndFlush(userResponses);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the userResponses
        restUserResponsesMockMvc
            .perform(delete(ENTITY_API_URL_ID, userResponses.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return userResponsesRepository.count();
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

    protected UserResponses getPersistedUserResponses(UserResponses userResponses) {
        return userResponsesRepository.findById(userResponses.getId()).orElseThrow();
    }

    protected void assertPersistedUserResponsesToMatchAllProperties(UserResponses expectedUserResponses) {
        assertUserResponsesAllPropertiesEquals(expectedUserResponses, getPersistedUserResponses(expectedUserResponses));
    }

    protected void assertPersistedUserResponsesToMatchUpdatableProperties(UserResponses expectedUserResponses) {
        assertUserResponsesAllUpdatablePropertiesEquals(expectedUserResponses, getPersistedUserResponses(expectedUserResponses));
    }
}
