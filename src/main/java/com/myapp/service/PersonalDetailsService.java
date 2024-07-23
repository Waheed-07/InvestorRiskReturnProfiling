package com.myapp.service;

import com.myapp.domain.PersonalDetails;
import com.myapp.repository.PersonalDetailsRepository;
import com.myapp.service.dto.PersonalDetailsDTO;
import com.myapp.service.mapper.PersonalDetailsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.myapp.domain.PersonalDetails}.
 */
@Service
@Transactional
public class PersonalDetailsService {

    private static final Logger log = LoggerFactory.getLogger(PersonalDetailsService.class);

    private final PersonalDetailsRepository personalDetailsRepository;

    private final PersonalDetailsMapper personalDetailsMapper;

    public PersonalDetailsService(PersonalDetailsRepository personalDetailsRepository, PersonalDetailsMapper personalDetailsMapper) {
        this.personalDetailsRepository = personalDetailsRepository;
        this.personalDetailsMapper = personalDetailsMapper;
    }

    /**
     * Save a personalDetails.
     *
     * @param personalDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonalDetailsDTO save(PersonalDetailsDTO personalDetailsDTO) {
        log.debug("Request to save PersonalDetails : {}", personalDetailsDTO);
        PersonalDetails personalDetails = personalDetailsMapper.toEntity(personalDetailsDTO);
        personalDetails = personalDetailsRepository.save(personalDetails);
        return personalDetailsMapper.toDto(personalDetails);
    }

    /**
     * Update a personalDetails.
     *
     * @param personalDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonalDetailsDTO update(PersonalDetailsDTO personalDetailsDTO) {
        log.debug("Request to update PersonalDetails : {}", personalDetailsDTO);
        PersonalDetails personalDetails = personalDetailsMapper.toEntity(personalDetailsDTO);
        personalDetails = personalDetailsRepository.save(personalDetails);
        return personalDetailsMapper.toDto(personalDetails);
    }

    /**
     * Partially update a personalDetails.
     *
     * @param personalDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PersonalDetailsDTO> partialUpdate(PersonalDetailsDTO personalDetailsDTO) {
        log.debug("Request to partially update PersonalDetails : {}", personalDetailsDTO);

        return personalDetailsRepository
            .findById(personalDetailsDTO.getId())
            .map(existingPersonalDetails -> {
                personalDetailsMapper.partialUpdate(existingPersonalDetails, personalDetailsDTO);

                return existingPersonalDetails;
            })
            .map(personalDetailsRepository::save)
            .map(personalDetailsMapper::toDto);
    }

    /**
     * Get all the personalDetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PersonalDetailsDTO> findAll() {
        log.debug("Request to get all PersonalDetails");
        return personalDetailsRepository
            .findAll()
            .stream()
            .map(personalDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one personalDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonalDetailsDTO> findOne(Long id) {
        log.debug("Request to get PersonalDetails : {}", id);
        return personalDetailsRepository.findById(id).map(personalDetailsMapper::toDto);
    }

    /**
     * Delete the personalDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PersonalDetails : {}", id);
        personalDetailsRepository.deleteById(id);
    }
}
