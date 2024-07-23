package com.myapp.service;

import com.myapp.domain.Options;
import com.myapp.repository.OptionsRepository;
import com.myapp.service.dto.OptionsDTO;
import com.myapp.service.mapper.OptionsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.myapp.domain.Options}.
 */
@Service
@Transactional
public class OptionsService {

    private static final Logger log = LoggerFactory.getLogger(OptionsService.class);

    private final OptionsRepository optionsRepository;

    private final OptionsMapper optionsMapper;

    public OptionsService(OptionsRepository optionsRepository, OptionsMapper optionsMapper) {
        this.optionsRepository = optionsRepository;
        this.optionsMapper = optionsMapper;
    }

    /**
     * Save a options.
     *
     * @param optionsDTO the entity to save.
     * @return the persisted entity.
     */
    public OptionsDTO save(OptionsDTO optionsDTO) {
        log.debug("Request to save Options : {}", optionsDTO);
        Options options = optionsMapper.toEntity(optionsDTO);
        options = optionsRepository.save(options);
        return optionsMapper.toDto(options);
    }

    /**
     * Update a options.
     *
     * @param optionsDTO the entity to save.
     * @return the persisted entity.
     */
    public OptionsDTO update(OptionsDTO optionsDTO) {
        log.debug("Request to update Options : {}", optionsDTO);
        Options options = optionsMapper.toEntity(optionsDTO);
        options = optionsRepository.save(options);
        return optionsMapper.toDto(options);
    }

    /**
     * Partially update a options.
     *
     * @param optionsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OptionsDTO> partialUpdate(OptionsDTO optionsDTO) {
        log.debug("Request to partially update Options : {}", optionsDTO);

        return optionsRepository
            .findById(optionsDTO.getId())
            .map(existingOptions -> {
                optionsMapper.partialUpdate(existingOptions, optionsDTO);

                return existingOptions;
            })
            .map(optionsRepository::save)
            .map(optionsMapper::toDto);
    }

    /**
     * Get all the options.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OptionsDTO> findAll() {
        log.debug("Request to get all Options");
        return optionsRepository.findAll().stream().map(optionsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one options by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OptionsDTO> findOne(Long id) {
        log.debug("Request to get Options : {}", id);
        return optionsRepository.findById(id).map(optionsMapper::toDto);
    }

    /**
     * Delete the options by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Options : {}", id);
        optionsRepository.deleteById(id);
    }
}
