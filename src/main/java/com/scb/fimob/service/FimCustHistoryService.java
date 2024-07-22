package com.scb.fimob.service;

import com.scb.fimob.domain.FimCustHistory;
import com.scb.fimob.repository.FimCustHistoryRepository;
import com.scb.fimob.service.dto.FimCustHistoryDTO;
import com.scb.fimob.service.mapper.FimCustHistoryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.FimCustHistory}.
 */
@Service
@Transactional
public class FimCustHistoryService {

    private static final Logger log = LoggerFactory.getLogger(FimCustHistoryService.class);

    private final FimCustHistoryRepository fimCustHistoryRepository;

    private final FimCustHistoryMapper fimCustHistoryMapper;

    public FimCustHistoryService(FimCustHistoryRepository fimCustHistoryRepository, FimCustHistoryMapper fimCustHistoryMapper) {
        this.fimCustHistoryRepository = fimCustHistoryRepository;
        this.fimCustHistoryMapper = fimCustHistoryMapper;
    }

    /**
     * Save a fimCustHistory.
     *
     * @param fimCustHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public FimCustHistoryDTO save(FimCustHistoryDTO fimCustHistoryDTO) {
        log.debug("Request to save FimCustHistory : {}", fimCustHistoryDTO);
        FimCustHistory fimCustHistory = fimCustHistoryMapper.toEntity(fimCustHistoryDTO);
        fimCustHistory = fimCustHistoryRepository.save(fimCustHistory);
        return fimCustHistoryMapper.toDto(fimCustHistory);
    }

    /**
     * Update a fimCustHistory.
     *
     * @param fimCustHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public FimCustHistoryDTO update(FimCustHistoryDTO fimCustHistoryDTO) {
        log.debug("Request to update FimCustHistory : {}", fimCustHistoryDTO);
        FimCustHistory fimCustHistory = fimCustHistoryMapper.toEntity(fimCustHistoryDTO);
        fimCustHistory = fimCustHistoryRepository.save(fimCustHistory);
        return fimCustHistoryMapper.toDto(fimCustHistory);
    }

    /**
     * Partially update a fimCustHistory.
     *
     * @param fimCustHistoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FimCustHistoryDTO> partialUpdate(FimCustHistoryDTO fimCustHistoryDTO) {
        log.debug("Request to partially update FimCustHistory : {}", fimCustHistoryDTO);

        return fimCustHistoryRepository
            .findById(fimCustHistoryDTO.getId())
            .map(existingFimCustHistory -> {
                fimCustHistoryMapper.partialUpdate(existingFimCustHistory, fimCustHistoryDTO);

                return existingFimCustHistory;
            })
            .map(fimCustHistoryRepository::save)
            .map(fimCustHistoryMapper::toDto);
    }

    /**
     * Get all the fimCustHistories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FimCustHistoryDTO> findAll() {
        log.debug("Request to get all FimCustHistories");
        return fimCustHistoryRepository
            .findAll()
            .stream()
            .map(fimCustHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fimCustHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FimCustHistoryDTO> findOne(Long id) {
        log.debug("Request to get FimCustHistory : {}", id);
        return fimCustHistoryRepository.findById(id).map(fimCustHistoryMapper::toDto);
    }

    /**
     * Delete the fimCustHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FimCustHistory : {}", id);
        fimCustHistoryRepository.deleteById(id);
    }
}
