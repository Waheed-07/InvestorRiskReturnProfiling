package com.scb.fimob.service;

import com.scb.fimob.domain.FimSettAcctHistory;
import com.scb.fimob.repository.FimSettAcctHistoryRepository;
import com.scb.fimob.service.dto.FimSettAcctHistoryDTO;
import com.scb.fimob.service.mapper.FimSettAcctHistoryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.FimSettAcctHistory}.
 */
@Service
@Transactional
public class FimSettAcctHistoryService {

    private static final Logger log = LoggerFactory.getLogger(FimSettAcctHistoryService.class);

    private final FimSettAcctHistoryRepository fimSettAcctHistoryRepository;

    private final FimSettAcctHistoryMapper fimSettAcctHistoryMapper;

    public FimSettAcctHistoryService(
        FimSettAcctHistoryRepository fimSettAcctHistoryRepository,
        FimSettAcctHistoryMapper fimSettAcctHistoryMapper
    ) {
        this.fimSettAcctHistoryRepository = fimSettAcctHistoryRepository;
        this.fimSettAcctHistoryMapper = fimSettAcctHistoryMapper;
    }

    /**
     * Save a fimSettAcctHistory.
     *
     * @param fimSettAcctHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public FimSettAcctHistoryDTO save(FimSettAcctHistoryDTO fimSettAcctHistoryDTO) {
        log.debug("Request to save FimSettAcctHistory : {}", fimSettAcctHistoryDTO);
        FimSettAcctHistory fimSettAcctHistory = fimSettAcctHistoryMapper.toEntity(fimSettAcctHistoryDTO);
        fimSettAcctHistory = fimSettAcctHistoryRepository.save(fimSettAcctHistory);
        return fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);
    }

    /**
     * Update a fimSettAcctHistory.
     *
     * @param fimSettAcctHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public FimSettAcctHistoryDTO update(FimSettAcctHistoryDTO fimSettAcctHistoryDTO) {
        log.debug("Request to update FimSettAcctHistory : {}", fimSettAcctHistoryDTO);
        FimSettAcctHistory fimSettAcctHistory = fimSettAcctHistoryMapper.toEntity(fimSettAcctHistoryDTO);
        fimSettAcctHistory = fimSettAcctHistoryRepository.save(fimSettAcctHistory);
        return fimSettAcctHistoryMapper.toDto(fimSettAcctHistory);
    }

    /**
     * Partially update a fimSettAcctHistory.
     *
     * @param fimSettAcctHistoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FimSettAcctHistoryDTO> partialUpdate(FimSettAcctHistoryDTO fimSettAcctHistoryDTO) {
        log.debug("Request to partially update FimSettAcctHistory : {}", fimSettAcctHistoryDTO);

        return fimSettAcctHistoryRepository
            .findById(fimSettAcctHistoryDTO.getId())
            .map(existingFimSettAcctHistory -> {
                fimSettAcctHistoryMapper.partialUpdate(existingFimSettAcctHistory, fimSettAcctHistoryDTO);

                return existingFimSettAcctHistory;
            })
            .map(fimSettAcctHistoryRepository::save)
            .map(fimSettAcctHistoryMapper::toDto);
    }

    /**
     * Get all the fimSettAcctHistories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FimSettAcctHistoryDTO> findAll() {
        log.debug("Request to get all FimSettAcctHistories");
        return fimSettAcctHistoryRepository
            .findAll()
            .stream()
            .map(fimSettAcctHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fimSettAcctHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FimSettAcctHistoryDTO> findOne(Long id) {
        log.debug("Request to get FimSettAcctHistory : {}", id);
        return fimSettAcctHistoryRepository.findById(id).map(fimSettAcctHistoryMapper::toDto);
    }

    /**
     * Delete the fimSettAcctHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FimSettAcctHistory : {}", id);
        fimSettAcctHistoryRepository.deleteById(id);
    }
}
