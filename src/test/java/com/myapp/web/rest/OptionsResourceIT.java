package com.myapp.web.rest;

import static com.myapp.domain.OptionsAsserts.*;
import static com.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.IntegrationTest;
import com.myapp.domain.Options;
import com.myapp.repository.OptionsRepository;
import com.myapp.service.dto.OptionsDTO;
import com.myapp.service.mapper.OptionsMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link OptionsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OptionsResourceIT {

    private static final String DEFAULT_OPTION_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_TEXT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/options";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OptionsRepository optionsRepository;

    @Autowired
    private OptionsMapper optionsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOptionsMockMvc;

    private Options options;

    private Options insertedOptions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Options createEntity(EntityManager em) {
        Options options = new Options().optionText(DEFAULT_OPTION_TEXT);
        return options;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Options createUpdatedEntity(EntityManager em) {
        Options options = new Options().optionText(UPDATED_OPTION_TEXT);
        return options;
    }

    @BeforeEach
    public void initTest() {
        options = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOptions != null) {
            optionsRepository.delete(insertedOptions);
            insertedOptions = null;
        }
    }

    @Test
    @Transactional
    void createOptions() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Options
        OptionsDTO optionsDTO = optionsMapper.toDto(options);
        var returnedOptionsDTO = om.readValue(
            restOptionsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(optionsDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OptionsDTO.class
        );

        // Validate the Options in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedOptions = optionsMapper.toEntity(returnedOptionsDTO);
        assertOptionsUpdatableFieldsEquals(returnedOptions, getPersistedOptions(returnedOptions));

        insertedOptions = returnedOptions;
    }

    @Test
    @Transactional
    void createOptionsWithExistingId() throws Exception {
        // Create the Options with an existing ID
        options.setId(1L);
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOptionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(optionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Options in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkOptionTextIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        options.setOptionText(null);

        // Create the Options, which fails.
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        restOptionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(optionsDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllOptions() throws Exception {
        // Initialize the database
        insertedOptions = optionsRepository.saveAndFlush(options);

        // Get all the optionsList
        restOptionsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(options.getId().intValue())))
            .andExpect(jsonPath("$.[*].optionText").value(hasItem(DEFAULT_OPTION_TEXT)));
    }

    @Test
    @Transactional
    void getOptions() throws Exception {
        // Initialize the database
        insertedOptions = optionsRepository.saveAndFlush(options);

        // Get the options
        restOptionsMockMvc
            .perform(get(ENTITY_API_URL_ID, options.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(options.getId().intValue()))
            .andExpect(jsonPath("$.optionText").value(DEFAULT_OPTION_TEXT));
    }

    @Test
    @Transactional
    void getNonExistingOptions() throws Exception {
        // Get the options
        restOptionsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOptions() throws Exception {
        // Initialize the database
        insertedOptions = optionsRepository.saveAndFlush(options);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the options
        Options updatedOptions = optionsRepository.findById(options.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOptions are not directly saved in db
        em.detach(updatedOptions);
        updatedOptions.optionText(UPDATED_OPTION_TEXT);
        OptionsDTO optionsDTO = optionsMapper.toDto(updatedOptions);

        restOptionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, optionsDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(optionsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Options in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOptionsToMatchAllProperties(updatedOptions);
    }

    @Test
    @Transactional
    void putNonExistingOptions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        options.setId(longCount.incrementAndGet());

        // Create the Options
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOptionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, optionsDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(optionsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Options in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOptions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        options.setId(longCount.incrementAndGet());

        // Create the Options
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(optionsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Options in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOptions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        options.setId(longCount.incrementAndGet());

        // Create the Options
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptionsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(optionsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Options in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOptionsWithPatch() throws Exception {
        // Initialize the database
        insertedOptions = optionsRepository.saveAndFlush(options);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the options using partial update
        Options partialUpdatedOptions = new Options();
        partialUpdatedOptions.setId(options.getId());

        restOptionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOptions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOptions))
            )
            .andExpect(status().isOk());

        // Validate the Options in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOptionsUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedOptions, options), getPersistedOptions(options));
    }

    @Test
    @Transactional
    void fullUpdateOptionsWithPatch() throws Exception {
        // Initialize the database
        insertedOptions = optionsRepository.saveAndFlush(options);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the options using partial update
        Options partialUpdatedOptions = new Options();
        partialUpdatedOptions.setId(options.getId());

        partialUpdatedOptions.optionText(UPDATED_OPTION_TEXT);

        restOptionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOptions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOptions))
            )
            .andExpect(status().isOk());

        // Validate the Options in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOptionsUpdatableFieldsEquals(partialUpdatedOptions, getPersistedOptions(partialUpdatedOptions));
    }

    @Test
    @Transactional
    void patchNonExistingOptions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        options.setId(longCount.incrementAndGet());

        // Create the Options
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOptionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, optionsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(optionsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Options in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOptions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        options.setId(longCount.incrementAndGet());

        // Create the Options
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(optionsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Options in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOptions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        options.setId(longCount.incrementAndGet());

        // Create the Options
        OptionsDTO optionsDTO = optionsMapper.toDto(options);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOptionsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(optionsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Options in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOptions() throws Exception {
        // Initialize the database
        insertedOptions = optionsRepository.saveAndFlush(options);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the options
        restOptionsMockMvc
            .perform(delete(ENTITY_API_URL_ID, options.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return optionsRepository.count();
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

    protected Options getPersistedOptions(Options options) {
        return optionsRepository.findById(options.getId()).orElseThrow();
    }

    protected void assertPersistedOptionsToMatchAllProperties(Options expectedOptions) {
        assertOptionsAllPropertiesEquals(expectedOptions, getPersistedOptions(expectedOptions));
    }

    protected void assertPersistedOptionsToMatchUpdatableProperties(Options expectedOptions) {
        assertOptionsAllUpdatablePropertiesEquals(expectedOptions, getPersistedOptions(expectedOptions));
    }
}
