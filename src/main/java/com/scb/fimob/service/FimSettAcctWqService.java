package com.scb.fimob.service;

import com.scb.fimob.domain.FimSettAcctWq;
import com.scb.fimob.repository.FimSettAcctWqRepository;
import com.scb.fimob.service.dto.FimSettAcctWqDTO;
import com.scb.fimob.service.mapper.FimSettAcctWqMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.FimSettAcctWq}.
 */
@Service
@Transactional
public class FimSettAcctWqService {

    private static final Logger log = LoggerFactory.getLogger(FimSettAcctWqService.class);

    private final FimSettAcctWqRepository fimSettAcctWqRepository;

    private final FimSettAcctWqMapper fimSettAcctWqMapper;

    public FimSettAcctWqService(FimSettAcctWqRepository fimSettAcctWqRepository, FimSettAcctWqMapper fimSettAcctWqMapper) {
        this.fimSettAcctWqRepository = fimSettAcctWqRepository;
        this.fimSettAcctWqMapper = fimSettAcctWqMapper;
    }

    /**
     * Save a fimSettAcctWq.
     *
     * @param fimSettAcctWqDTO the entity to save.
     * @return the persisted entity.
     */
    public FimSettAcctWqDTO save(FimSettAcctWqDTO fimSettAcctWqDTO) {
        log.debug("Request to save FimSettAcctWq : {}", fimSettAcctWqDTO);
        FimSettAcctWq fimSettAcctWq = fimSettAcctWqMapper.toEntity(fimSettAcctWqDTO);
        fimSettAcctWq = fimSettAcctWqRepository.save(fimSettAcctWq);
        return fimSettAcctWqMapper.toDto(fimSettAcctWq);
    }

    /**
     * Update a fimSettAcctWq.
     *
     * @param fimSettAcctWqDTO the entity to save.
     * @return the persisted entity.
     */
    public FimSettAcctWqDTO update(FimSettAcctWqDTO fimSettAcctWqDTO) {
        log.debug("Request to update FimSettAcctWq : {}", fimSettAcctWqDTO);
        FimSettAcctWq fimSettAcctWq = fimSettAcctWqMapper.toEntity(fimSettAcctWqDTO);
        fimSettAcctWq = fimSettAcctWqRepository.save(fimSettAcctWq);
        return fimSettAcctWqMapper.toDto(fimSettAcctWq);
    }

    /**
     * Partially update a fimSettAcctWq.
     *
     * @param fimSettAcctWqDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FimSettAcctWqDTO> partialUpdate(FimSettAcctWqDTO fimSettAcctWqDTO) {
        log.debug("Request to partially update FimSettAcctWq : {}", fimSettAcctWqDTO);

        return fimSettAcctWqRepository
            .findById(fimSettAcctWqDTO.getId())
            .map(existingFimSettAcctWq -> {
                fimSettAcctWqMapper.partialUpdate(existingFimSettAcctWq, fimSettAcctWqDTO);

                return existingFimSettAcctWq;
            })
            .map(fimSettAcctWqRepository::save)
            .map(fimSettAcctWqMapper::toDto);
    }

    /**
     * Get all the fimSettAcctWqs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FimSettAcctWqDTO> findAll() {
        log.debug("Request to get all FimSettAcctWqs");
        return fimSettAcctWqRepository.findAll().stream().map(fimSettAcctWqMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fimSettAcctWq by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FimSettAcctWqDTO> findOne(Long id) {
        log.debug("Request to get FimSettAcctWq : {}", id);
        return fimSettAcctWqRepository.findById(id).map(fimSettAcctWqMapper::toDto);
    }

    /**
     * Delete the fimSettAcctWq by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FimSettAcctWq : {}", id);
        fimSettAcctWqRepository.deleteById(id);
    }
}
