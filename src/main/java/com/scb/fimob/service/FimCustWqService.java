package com.scb.fimob.service;

import com.scb.fimob.domain.FimCustWq;
import com.scb.fimob.repository.FimCustWqRepository;
import com.scb.fimob.service.dto.FimCustWqDTO;
import com.scb.fimob.service.mapper.FimCustWqMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.FimCustWq}.
 */
@Service
@Transactional
public class FimCustWqService {

    private static final Logger log = LoggerFactory.getLogger(FimCustWqService.class);

    private final FimCustWqRepository fimCustWqRepository;

    private final FimCustWqMapper fimCustWqMapper;

    public FimCustWqService(FimCustWqRepository fimCustWqRepository, FimCustWqMapper fimCustWqMapper) {
        this.fimCustWqRepository = fimCustWqRepository;
        this.fimCustWqMapper = fimCustWqMapper;
    }

    /**
     * Save a fimCustWq.
     *
     * @param fimCustWqDTO the entity to save.
     * @return the persisted entity.
     */
    public FimCustWqDTO save(FimCustWqDTO fimCustWqDTO) {
        log.debug("Request to save FimCustWq : {}", fimCustWqDTO);
        FimCustWq fimCustWq = fimCustWqMapper.toEntity(fimCustWqDTO);
        fimCustWq = fimCustWqRepository.save(fimCustWq);
        return fimCustWqMapper.toDto(fimCustWq);
    }

    /**
     * Update a fimCustWq.
     *
     * @param fimCustWqDTO the entity to save.
     * @return the persisted entity.
     */
    public FimCustWqDTO update(FimCustWqDTO fimCustWqDTO) {
        log.debug("Request to update FimCustWq : {}", fimCustWqDTO);
        FimCustWq fimCustWq = fimCustWqMapper.toEntity(fimCustWqDTO);
        fimCustWq = fimCustWqRepository.save(fimCustWq);
        return fimCustWqMapper.toDto(fimCustWq);
    }

    /**
     * Partially update a fimCustWq.
     *
     * @param fimCustWqDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FimCustWqDTO> partialUpdate(FimCustWqDTO fimCustWqDTO) {
        log.debug("Request to partially update FimCustWq : {}", fimCustWqDTO);

        return fimCustWqRepository
            .findById(fimCustWqDTO.getId())
            .map(existingFimCustWq -> {
                fimCustWqMapper.partialUpdate(existingFimCustWq, fimCustWqDTO);

                return existingFimCustWq;
            })
            .map(fimCustWqRepository::save)
            .map(fimCustWqMapper::toDto);
    }

    /**
     * Get all the fimCustWqs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FimCustWqDTO> findAll() {
        log.debug("Request to get all FimCustWqs");
        return fimCustWqRepository.findAll().stream().map(fimCustWqMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fimCustWq by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FimCustWqDTO> findOne(Long id) {
        log.debug("Request to get FimCustWq : {}", id);
        return fimCustWqRepository.findById(id).map(fimCustWqMapper::toDto);
    }

    /**
     * Delete the fimCustWq by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FimCustWq : {}", id);
        fimCustWqRepository.deleteById(id);
    }
}
