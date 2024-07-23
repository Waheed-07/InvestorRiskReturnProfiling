package com.myapp.web.rest;

import static com.myapp.domain.QuestionsAsserts.*;
import static com.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.IntegrationTest;
import com.myapp.domain.Questions;
import com.myapp.repository.QuestionsRepository;
import com.myapp.service.dto.QuestionsDTO;
import com.myapp.service.mapper.QuestionsMapper;
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
 * Integration tests for the {@link QuestionsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionsResourceIT {

    private static final String DEFAULT_QUESTION_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_TYPE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionsMockMvc;

    private Questions questions;

    private Questions insertedQuestions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Questions createEntity(EntityManager em) {
        Questions questions = new Questions()
            .questionText(DEFAULT_QUESTION_TEXT)
            .questionType(DEFAULT_QUESTION_TYPE)
            .createdAt(DEFAULT_CREATED_AT);
        return questions;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Questions createUpdatedEntity(EntityManager em) {
        Questions questions = new Questions()
            .questionText(UPDATED_QUESTION_TEXT)
            .questionType(UPDATED_QUESTION_TYPE)
            .createdAt(UPDATED_CREATED_AT);
        return questions;
    }

    @BeforeEach
    public void initTest() {
        questions = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedQuestions != null) {
            questionsRepository.delete(insertedQuestions);
            insertedQuestions = null;
        }
    }

    @Test
    @Transactional
    void createQuestions() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Questions
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);
        var returnedQuestionsDTO = om.readValue(
            restQuestionsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(questionsDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QuestionsDTO.class
        );

        // Validate the Questions in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedQuestions = questionsMapper.toEntity(returnedQuestionsDTO);
        assertQuestionsUpdatableFieldsEquals(returnedQuestions, getPersistedQuestions(returnedQuestions));

        insertedQuestions = returnedQuestions;
    }

    @Test
    @Transactional
    void createQuestionsWithExistingId() throws Exception {
        // Create the Questions with an existing ID
        questions.setId(1L);
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(questionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Questions in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkQuestionTextIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        questions.setQuestionText(null);

        // Create the Questions, which fails.
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        restQuestionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(questionsDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQuestionTypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        questions.setQuestionType(null);

        // Create the Questions, which fails.
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        restQuestionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(questionsDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        questions.setCreatedAt(null);

        // Create the Questions, which fails.
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        restQuestionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(questionsDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllQuestions() throws Exception {
        // Initialize the database
        insertedQuestions = questionsRepository.saveAndFlush(questions);

        // Get all the questionsList
        restQuestionsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questions.getId().intValue())))
            .andExpect(jsonPath("$.[*].questionText").value(hasItem(DEFAULT_QUESTION_TEXT)))
            .andExpect(jsonPath("$.[*].questionType").value(hasItem(DEFAULT_QUESTION_TYPE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())));
    }

    @Test
    @Transactional
    void getQuestions() throws Exception {
        // Initialize the database
        insertedQuestions = questionsRepository.saveAndFlush(questions);

        // Get the questions
        restQuestionsMockMvc
            .perform(get(ENTITY_API_URL_ID, questions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questions.getId().intValue()))
            .andExpect(jsonPath("$.questionText").value(DEFAULT_QUESTION_TEXT))
            .andExpect(jsonPath("$.questionType").value(DEFAULT_QUESTION_TYPE))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQuestions() throws Exception {
        // Get the questions
        restQuestionsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuestions() throws Exception {
        // Initialize the database
        insertedQuestions = questionsRepository.saveAndFlush(questions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the questions
        Questions updatedQuestions = questionsRepository.findById(questions.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQuestions are not directly saved in db
        em.detach(updatedQuestions);
        updatedQuestions.questionText(UPDATED_QUESTION_TEXT).questionType(UPDATED_QUESTION_TYPE).createdAt(UPDATED_CREATED_AT);
        QuestionsDTO questionsDTO = questionsMapper.toDto(updatedQuestions);

        restQuestionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(questionsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Questions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQuestionsToMatchAllProperties(updatedQuestions);
    }

    @Test
    @Transactional
    void putNonExistingQuestions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        questions.setId(longCount.incrementAndGet());

        // Create the Questions
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(questionsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Questions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuestions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        questions.setId(longCount.incrementAndGet());

        // Create the Questions
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(questionsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Questions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuestions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        questions.setId(longCount.incrementAndGet());

        // Create the Questions
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(questionsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Questions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuestionsWithPatch() throws Exception {
        // Initialize the database
        insertedQuestions = questionsRepository.saveAndFlush(questions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the questions using partial update
        Questions partialUpdatedQuestions = new Questions();
        partialUpdatedQuestions.setId(questions.getId());

        partialUpdatedQuestions.questionText(UPDATED_QUESTION_TEXT);

        restQuestionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuestions))
            )
            .andExpect(status().isOk());

        // Validate the Questions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuestionsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQuestions, questions),
            getPersistedQuestions(questions)
        );
    }

    @Test
    @Transactional
    void fullUpdateQuestionsWithPatch() throws Exception {
        // Initialize the database
        insertedQuestions = questionsRepository.saveAndFlush(questions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the questions using partial update
        Questions partialUpdatedQuestions = new Questions();
        partialUpdatedQuestions.setId(questions.getId());

        partialUpdatedQuestions.questionText(UPDATED_QUESTION_TEXT).questionType(UPDATED_QUESTION_TYPE).createdAt(UPDATED_CREATED_AT);

        restQuestionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQuestions))
            )
            .andExpect(status().isOk());

        // Validate the Questions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuestionsUpdatableFieldsEquals(partialUpdatedQuestions, getPersistedQuestions(partialUpdatedQuestions));
    }

    @Test
    @Transactional
    void patchNonExistingQuestions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        questions.setId(longCount.incrementAndGet());

        // Create the Questions
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, questionsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(questionsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Questions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuestions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        questions.setId(longCount.incrementAndGet());

        // Create the Questions
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(questionsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Questions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuestions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        questions.setId(longCount.incrementAndGet());

        // Create the Questions
        QuestionsDTO questionsDTO = questionsMapper.toDto(questions);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(questionsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Questions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuestions() throws Exception {
        // Initialize the database
        insertedQuestions = questionsRepository.saveAndFlush(questions);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the questions
        restQuestionsMockMvc
            .perform(delete(ENTITY_API_URL_ID, questions.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return questionsRepository.count();
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

    protected Questions getPersistedQuestions(Questions questions) {
        return questionsRepository.findById(questions.getId()).orElseThrow();
    }

    protected void assertPersistedQuestionsToMatchAllProperties(Questions expectedQuestions) {
        assertQuestionsAllPropertiesEquals(expectedQuestions, getPersistedQuestions(expectedQuestions));
    }

    protected void assertPersistedQuestionsToMatchUpdatableProperties(Questions expectedQuestions) {
        assertQuestionsAllUpdatablePropertiesEquals(expectedQuestions, getPersistedQuestions(expectedQuestions));
    }
}
