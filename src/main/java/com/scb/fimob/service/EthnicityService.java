package com.scb.fimob.service;

import com.scb.fimob.domain.Ethnicity;
import com.scb.fimob.repository.EthnicityRepository;
import com.scb.fimob.service.dto.EthnicityDTO;
import com.scb.fimob.service.mapper.EthnicityMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.Ethnicity}.
 */
@Service
@Transactional
public class EthnicityService {

    private static final Logger log = LoggerFactory.getLogger(EthnicityService.class);

    private final EthnicityRepository ethnicityRepository;

    private final EthnicityMapper ethnicityMapper;

    public EthnicityService(EthnicityRepository ethnicityRepository, EthnicityMapper ethnicityMapper) {
        this.ethnicityRepository = ethnicityRepository;
        this.ethnicityMapper = ethnicityMapper;
    }

    /**
     * Save a ethnicity.
     *
     * @param ethnicityDTO the entity to save.
     * @return the persisted entity.
     */
    public EthnicityDTO save(EthnicityDTO ethnicityDTO) {
        log.debug("Request to save Ethnicity : {}", ethnicityDTO);
        Ethnicity ethnicity = ethnicityMapper.toEntity(ethnicityDTO);
        ethnicity = ethnicityRepository.save(ethnicity);
        return ethnicityMapper.toDto(ethnicity);
    }

    /**
     * Update a ethnicity.
     *
     * @param ethnicityDTO the entity to save.
     * @return the persisted entity.
     */
    public EthnicityDTO update(EthnicityDTO ethnicityDTO) {
        log.debug("Request to update Ethnicity : {}", ethnicityDTO);
        Ethnicity ethnicity = ethnicityMapper.toEntity(ethnicityDTO);
        ethnicity = ethnicityRepository.save(ethnicity);
        return ethnicityMapper.toDto(ethnicity);
    }

    /**
     * Partially update a ethnicity.
     *
     * @param ethnicityDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EthnicityDTO> partialUpdate(EthnicityDTO ethnicityDTO) {
        log.debug("Request to partially update Ethnicity : {}", ethnicityDTO);

        return ethnicityRepository
            .findById(ethnicityDTO.getId())
            .map(existingEthnicity -> {
                ethnicityMapper.partialUpdate(existingEthnicity, ethnicityDTO);

                return existingEthnicity;
            })
            .map(ethnicityRepository::save)
            .map(ethnicityMapper::toDto);
    }

    /**
     * Get all the ethnicities.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EthnicityDTO> findAll() {
        log.debug("Request to get all Ethnicities");
        return ethnicityRepository.findAll().stream().map(ethnicityMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ethnicity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EthnicityDTO> findOne(Long id) {
        log.debug("Request to get Ethnicity : {}", id);
        return ethnicityRepository.findById(id).map(ethnicityMapper::toDto);
    }

    /**
     * Delete the ethnicity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ethnicity : {}", id);
        ethnicityRepository.deleteById(id);
    }
}
