package com.scb.fimob.service;

import com.scb.fimob.domain.FimAccountsHistory;
import com.scb.fimob.repository.FimAccountsHistoryRepository;
import com.scb.fimob.service.dto.FimAccountsHistoryDTO;
import com.scb.fimob.service.mapper.FimAccountsHistoryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.FimAccountsHistory}.
 */
@Service
@Transactional
public class FimAccountsHistoryService {

    private static final Logger log = LoggerFactory.getLogger(FimAccountsHistoryService.class);

    private final FimAccountsHistoryRepository fimAccountsHistoryRepository;

    private final FimAccountsHistoryMapper fimAccountsHistoryMapper;

    public FimAccountsHistoryService(
        FimAccountsHistoryRepository fimAccountsHistoryRepository,
        FimAccountsHistoryMapper fimAccountsHistoryMapper
    ) {
        this.fimAccountsHistoryRepository = fimAccountsHistoryRepository;
        this.fimAccountsHistoryMapper = fimAccountsHistoryMapper;
    }

    /**
     * Save a fimAccountsHistory.
     *
     * @param fimAccountsHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public FimAccountsHistoryDTO save(FimAccountsHistoryDTO fimAccountsHistoryDTO) {
        log.debug("Request to save FimAccountsHistory : {}", fimAccountsHistoryDTO);
        FimAccountsHistory fimAccountsHistory = fimAccountsHistoryMapper.toEntity(fimAccountsHistoryDTO);
        fimAccountsHistory = fimAccountsHistoryRepository.save(fimAccountsHistory);
        return fimAccountsHistoryMapper.toDto(fimAccountsHistory);
    }

    /**
     * Update a fimAccountsHistory.
     *
     * @param fimAccountsHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public FimAccountsHistoryDTO update(FimAccountsHistoryDTO fimAccountsHistoryDTO) {
        log.debug("Request to update FimAccountsHistory : {}", fimAccountsHistoryDTO);
        FimAccountsHistory fimAccountsHistory = fimAccountsHistoryMapper.toEntity(fimAccountsHistoryDTO);
        fimAccountsHistory = fimAccountsHistoryRepository.save(fimAccountsHistory);
        return fimAccountsHistoryMapper.toDto(fimAccountsHistory);
    }

    /**
     * Partially update a fimAccountsHistory.
     *
     * @param fimAccountsHistoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FimAccountsHistoryDTO> partialUpdate(FimAccountsHistoryDTO fimAccountsHistoryDTO) {
        log.debug("Request to partially update FimAccountsHistory : {}", fimAccountsHistoryDTO);

        return fimAccountsHistoryRepository
            .findById(fimAccountsHistoryDTO.getId())
            .map(existingFimAccountsHistory -> {
                fimAccountsHistoryMapper.partialUpdate(existingFimAccountsHistory, fimAccountsHistoryDTO);

                return existingFimAccountsHistory;
            })
            .map(fimAccountsHistoryRepository::save)
            .map(fimAccountsHistoryMapper::toDto);
    }

    /**
     * Get all the fimAccountsHistories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FimAccountsHistoryDTO> findAll() {
        log.debug("Request to get all FimAccountsHistories");
        return fimAccountsHistoryRepository
            .findAll()
            .stream()
            .map(fimAccountsHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fimAccountsHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FimAccountsHistoryDTO> findOne(Long id) {
        log.debug("Request to get FimAccountsHistory : {}", id);
        return fimAccountsHistoryRepository.findById(id).map(fimAccountsHistoryMapper::toDto);
    }

    /**
     * Delete the fimAccountsHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FimAccountsHistory : {}", id);
        fimAccountsHistoryRepository.deleteById(id);
    }
}
