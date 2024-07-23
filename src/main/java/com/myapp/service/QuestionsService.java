package com.myapp.service;

import com.myapp.domain.Questions;
import com.myapp.repository.QuestionsRepository;
import com.myapp.service.dto.QuestionsDTO;
import com.myapp.service.mapper.QuestionsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.myapp.domain.Questions}.
 */
@Service
@Transactional
public class QuestionsService {

    private static final Logger log = LoggerFactory.getLogger(QuestionsService.class);

    private final QuestionsRepository questionsRepository;

    private final QuestionsMapper questionsMapper;

    public QuestionsService(QuestionsRepository questionsRepository, QuestionsMapper questionsMapper) {
        this.questionsRepository = questionsRepository;
        this.questionsMapper = questionsMapper;
    }

    /**
     * Save a questions.
     *
     * @param questionsDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionsDTO save(QuestionsDTO questionsDTO) {
        log.debug("Request to save Questions : {}", questionsDTO);
        Questions questions = questionsMapper.toEntity(questionsDTO);
        questions = questionsRepository.save(questions);
        return questionsMapper.toDto(questions);
    }

    /**
     * Update a questions.
     *
     * @param questionsDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionsDTO update(QuestionsDTO questionsDTO) {
        log.debug("Request to update Questions : {}", questionsDTO);
        Questions questions = questionsMapper.toEntity(questionsDTO);
        questions = questionsRepository.save(questions);
        return questionsMapper.toDto(questions);
    }

    /**
     * Partially update a questions.
     *
     * @param questionsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QuestionsDTO> partialUpdate(QuestionsDTO questionsDTO) {
        log.debug("Request to partially update Questions : {}", questionsDTO);

        return questionsRepository
            .findById(questionsDTO.getId())
            .map(existingQuestions -> {
                questionsMapper.partialUpdate(existingQuestions, questionsDTO);

                return existingQuestions;
            })
            .map(questionsRepository::save)
            .map(questionsMapper::toDto);
    }

    /**
     * Get all the questions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<QuestionsDTO> findAll() {
        log.debug("Request to get all Questions");
        return questionsRepository.findAll().stream().map(questionsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one questions by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuestionsDTO> findOne(Long id) {
        log.debug("Request to get Questions : {}", id);
        return questionsRepository.findById(id).map(questionsMapper::toDto);
    }

    /**
     * Delete the questions by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Questions : {}", id);
        questionsRepository.deleteById(id);
    }
}
