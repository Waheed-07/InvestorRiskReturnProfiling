package com.myapp.service;

import com.myapp.domain.Profession;
import com.myapp.repository.ProfessionRepository;
import com.myapp.service.dto.ProfessionDTO;
import com.myapp.service.mapper.ProfessionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.myapp.domain.Profession}.
 */
@Service
@Transactional
public class ProfessionService {

    private static final Logger log = LoggerFactory.getLogger(ProfessionService.class);

    private final ProfessionRepository professionRepository;

    private final ProfessionMapper professionMapper;

    public ProfessionService(ProfessionRepository professionRepository, ProfessionMapper professionMapper) {
        this.professionRepository = professionRepository;
        this.professionMapper = professionMapper;
    }

    /**
     * Save a profession.
     *
     * @param professionDTO the entity to save.
     * @return the persisted entity.
     */
    public ProfessionDTO save(ProfessionDTO professionDTO) {
        log.debug("Request to save Profession : {}", professionDTO);
        Profession profession = professionMapper.toEntity(professionDTO);
        profession = professionRepository.save(profession);
        return professionMapper.toDto(profession);
    }

    /**
     * Update a profession.
     *
     * @param professionDTO the entity to save.
     * @return the persisted entity.
     */
    public ProfessionDTO update(ProfessionDTO professionDTO) {
        log.debug("Request to update Profession : {}", professionDTO);
        Profession profession = professionMapper.toEntity(professionDTO);
        profession = professionRepository.save(profession);
        return professionMapper.toDto(profession);
    }

    /**
     * Partially update a profession.
     *
     * @param professionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProfessionDTO> partialUpdate(ProfessionDTO professionDTO) {
        log.debug("Request to partially update Profession : {}", professionDTO);

        return professionRepository
            .findById(professionDTO.getId())
            .map(existingProfession -> {
                professionMapper.partialUpdate(existingProfession, professionDTO);

                return existingProfession;
            })
            .map(professionRepository::save)
            .map(professionMapper::toDto);
    }

    /**
     * Get all the professions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProfessionDTO> findAll() {
        log.debug("Request to get all Professions");
        return professionRepository.findAll().stream().map(professionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one profession by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProfessionDTO> findOne(Long id) {
        log.debug("Request to get Profession : {}", id);
        return professionRepository.findById(id).map(professionMapper::toDto);
    }

    /**
     * Delete the profession by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Profession : {}", id);
        professionRepository.deleteById(id);
    }
}
