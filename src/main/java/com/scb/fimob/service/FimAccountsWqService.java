package com.scb.fimob.service;

import com.scb.fimob.domain.FimAccountsWq;
import com.scb.fimob.repository.FimAccountsWqRepository;
import com.scb.fimob.service.dto.FimAccountsWqDTO;
import com.scb.fimob.service.mapper.FimAccountsWqMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.FimAccountsWq}.
 */
@Service
@Transactional
public class FimAccountsWqService {

    private static final Logger log = LoggerFactory.getLogger(FimAccountsWqService.class);

    private final FimAccountsWqRepository fimAccountsWqRepository;

    private final FimAccountsWqMapper fimAccountsWqMapper;

    public FimAccountsWqService(FimAccountsWqRepository fimAccountsWqRepository, FimAccountsWqMapper fimAccountsWqMapper) {
        this.fimAccountsWqRepository = fimAccountsWqRepository;
        this.fimAccountsWqMapper = fimAccountsWqMapper;
    }

    /**
     * Save a fimAccountsWq.
     *
     * @param fimAccountsWqDTO the entity to save.
     * @return the persisted entity.
     */
    public FimAccountsWqDTO save(FimAccountsWqDTO fimAccountsWqDTO) {
        log.debug("Request to save FimAccountsWq : {}", fimAccountsWqDTO);
        FimAccountsWq fimAccountsWq = fimAccountsWqMapper.toEntity(fimAccountsWqDTO);
        fimAccountsWq = fimAccountsWqRepository.save(fimAccountsWq);
        return fimAccountsWqMapper.toDto(fimAccountsWq);
    }

    /**
     * Update a fimAccountsWq.
     *
     * @param fimAccountsWqDTO the entity to save.
     * @return the persisted entity.
     */
    public FimAccountsWqDTO update(FimAccountsWqDTO fimAccountsWqDTO) {
        log.debug("Request to update FimAccountsWq : {}", fimAccountsWqDTO);
        FimAccountsWq fimAccountsWq = fimAccountsWqMapper.toEntity(fimAccountsWqDTO);
        fimAccountsWq = fimAccountsWqRepository.save(fimAccountsWq);
        return fimAccountsWqMapper.toDto(fimAccountsWq);
    }

    /**
     * Partially update a fimAccountsWq.
     *
     * @param fimAccountsWqDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FimAccountsWqDTO> partialUpdate(FimAccountsWqDTO fimAccountsWqDTO) {
        log.debug("Request to partially update FimAccountsWq : {}", fimAccountsWqDTO);

        return fimAccountsWqRepository
            .findById(fimAccountsWqDTO.getId())
            .map(existingFimAccountsWq -> {
                fimAccountsWqMapper.partialUpdate(existingFimAccountsWq, fimAccountsWqDTO);

                return existingFimAccountsWq;
            })
            .map(fimAccountsWqRepository::save)
            .map(fimAccountsWqMapper::toDto);
    }

    /**
     * Get all the fimAccountsWqs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FimAccountsWqDTO> findAll() {
        log.debug("Request to get all FimAccountsWqs");
        return fimAccountsWqRepository.findAll().stream().map(fimAccountsWqMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fimAccountsWq by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FimAccountsWqDTO> findOne(Long id) {
        log.debug("Request to get FimAccountsWq : {}", id);
        return fimAccountsWqRepository.findById(id).map(fimAccountsWqMapper::toDto);
    }

    /**
     * Delete the fimAccountsWq by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FimAccountsWq : {}", id);
        fimAccountsWqRepository.deleteById(id);
    }
}
