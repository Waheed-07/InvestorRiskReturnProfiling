package com.scb.fimob.service;

import com.scb.fimob.domain.FimSettAcct;
import com.scb.fimob.repository.FimSettAcctRepository;
import com.scb.fimob.service.dto.FimSettAcctDTO;
import com.scb.fimob.service.mapper.FimSettAcctMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.FimSettAcct}.
 */
@Service
@Transactional
public class FimSettAcctService {

    private static final Logger log = LoggerFactory.getLogger(FimSettAcctService.class);

    private final FimSettAcctRepository fimSettAcctRepository;

    private final FimSettAcctMapper fimSettAcctMapper;

    public FimSettAcctService(FimSettAcctRepository fimSettAcctRepository, FimSettAcctMapper fimSettAcctMapper) {
        this.fimSettAcctRepository = fimSettAcctRepository;
        this.fimSettAcctMapper = fimSettAcctMapper;
    }

    /**
     * Save a fimSettAcct.
     *
     * @param fimSettAcctDTO the entity to save.
     * @return the persisted entity.
     */
    public FimSettAcctDTO save(FimSettAcctDTO fimSettAcctDTO) {
        log.debug("Request to save FimSettAcct : {}", fimSettAcctDTO);
        FimSettAcct fimSettAcct = fimSettAcctMapper.toEntity(fimSettAcctDTO);
        fimSettAcct = fimSettAcctRepository.save(fimSettAcct);
        return fimSettAcctMapper.toDto(fimSettAcct);
    }

    /**
     * Update a fimSettAcct.
     *
     * @param fimSettAcctDTO the entity to save.
     * @return the persisted entity.
     */
    public FimSettAcctDTO update(FimSettAcctDTO fimSettAcctDTO) {
        log.debug("Request to update FimSettAcct : {}", fimSettAcctDTO);
        FimSettAcct fimSettAcct = fimSettAcctMapper.toEntity(fimSettAcctDTO);
        fimSettAcct = fimSettAcctRepository.save(fimSettAcct);
        return fimSettAcctMapper.toDto(fimSettAcct);
    }

    /**
     * Partially update a fimSettAcct.
     *
     * @param fimSettAcctDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FimSettAcctDTO> partialUpdate(FimSettAcctDTO fimSettAcctDTO) {
        log.debug("Request to partially update FimSettAcct : {}", fimSettAcctDTO);

        return fimSettAcctRepository
            .findById(fimSettAcctDTO.getId())
            .map(existingFimSettAcct -> {
                fimSettAcctMapper.partialUpdate(existingFimSettAcct, fimSettAcctDTO);

                return existingFimSettAcct;
            })
            .map(fimSettAcctRepository::save)
            .map(fimSettAcctMapper::toDto);
    }

    /**
     * Get all the fimSettAccts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FimSettAcctDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FimSettAccts");
        return fimSettAcctRepository.findAll(pageable).map(fimSettAcctMapper::toDto);
    }

    /**
     * Get one fimSettAcct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FimSettAcctDTO> findOne(Long id) {
        log.debug("Request to get FimSettAcct : {}", id);
        return fimSettAcctRepository.findById(id).map(fimSettAcctMapper::toDto);
    }

    /**
     * Delete the fimSettAcct by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FimSettAcct : {}", id);
        fimSettAcctRepository.deleteById(id);
    }
}
