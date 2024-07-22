package com.scb.fimob.service;

import com.scb.fimob.domain.FimCust;
import com.scb.fimob.repository.FimCustRepository;
import com.scb.fimob.service.dto.FimCustDTO;
import com.scb.fimob.service.mapper.FimCustMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.FimCust}.
 */
@Service
@Transactional
public class FimCustService {

    private static final Logger log = LoggerFactory.getLogger(FimCustService.class);

    private final FimCustRepository fimCustRepository;

    private final FimCustMapper fimCustMapper;

    public FimCustService(FimCustRepository fimCustRepository, FimCustMapper fimCustMapper) {
        this.fimCustRepository = fimCustRepository;
        this.fimCustMapper = fimCustMapper;
    }

    /**
     * Save a fimCust.
     *
     * @param fimCustDTO the entity to save.
     * @return the persisted entity.
     */
    public FimCustDTO save(FimCustDTO fimCustDTO) {
        log.debug("Request to save FimCust : {}", fimCustDTO);
        FimCust fimCust = fimCustMapper.toEntity(fimCustDTO);
        fimCust = fimCustRepository.save(fimCust);
        return fimCustMapper.toDto(fimCust);
    }

    /**
     * Update a fimCust.
     *
     * @param fimCustDTO the entity to save.
     * @return the persisted entity.
     */
    public FimCustDTO update(FimCustDTO fimCustDTO) {
        log.debug("Request to update FimCust : {}", fimCustDTO);
        FimCust fimCust = fimCustMapper.toEntity(fimCustDTO);
        fimCust = fimCustRepository.save(fimCust);
        return fimCustMapper.toDto(fimCust);
    }

    /**
     * Partially update a fimCust.
     *
     * @param fimCustDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FimCustDTO> partialUpdate(FimCustDTO fimCustDTO) {
        log.debug("Request to partially update FimCust : {}", fimCustDTO);

        return fimCustRepository
            .findById(fimCustDTO.getId())
            .map(existingFimCust -> {
                fimCustMapper.partialUpdate(existingFimCust, fimCustDTO);

                return existingFimCust;
            })
            .map(fimCustRepository::save)
            .map(fimCustMapper::toDto);
    }

    /**
     * Get all the fimCusts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FimCustDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FimCusts");
        return fimCustRepository.findAll(pageable).map(fimCustMapper::toDto);
    }

    /**
     * Get one fimCust by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FimCustDTO> findOne(Long id) {
        log.debug("Request to get FimCust : {}", id);
        return fimCustRepository.findById(id).map(fimCustMapper::toDto);
    }

    /**
     * Delete the fimCust by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FimCust : {}", id);
        fimCustRepository.deleteById(id);
    }
}
