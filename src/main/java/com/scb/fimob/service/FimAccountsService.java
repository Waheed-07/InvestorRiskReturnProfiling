package com.scb.fimob.service;

import com.scb.fimob.domain.FimAccounts;
import com.scb.fimob.repository.FimAccountsRepository;
import com.scb.fimob.service.dto.FimAccountsDTO;
import com.scb.fimob.service.mapper.FimAccountsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.scb.fimob.domain.FimAccounts}.
 */
@Service
@Transactional
public class FimAccountsService {

    private static final Logger log = LoggerFactory.getLogger(FimAccountsService.class);

    private final FimAccountsRepository fimAccountsRepository;

    private final FimAccountsMapper fimAccountsMapper;

    public FimAccountsService(FimAccountsRepository fimAccountsRepository, FimAccountsMapper fimAccountsMapper) {
        this.fimAccountsRepository = fimAccountsRepository;
        this.fimAccountsMapper = fimAccountsMapper;
    }

    /**
     * Save a fimAccounts.
     *
     * @param fimAccountsDTO the entity to save.
     * @return the persisted entity.
     */
    public FimAccountsDTO save(FimAccountsDTO fimAccountsDTO) {
        log.debug("Request to save FimAccounts : {}", fimAccountsDTO);
        FimAccounts fimAccounts = fimAccountsMapper.toEntity(fimAccountsDTO);
        fimAccounts = fimAccountsRepository.save(fimAccounts);
        return fimAccountsMapper.toDto(fimAccounts);
    }

    /**
     * Update a fimAccounts.
     *
     * @param fimAccountsDTO the entity to save.
     * @return the persisted entity.
     */
    public FimAccountsDTO update(FimAccountsDTO fimAccountsDTO) {
        log.debug("Request to update FimAccounts : {}", fimAccountsDTO);
        FimAccounts fimAccounts = fimAccountsMapper.toEntity(fimAccountsDTO);
        fimAccounts = fimAccountsRepository.save(fimAccounts);
        return fimAccountsMapper.toDto(fimAccounts);
    }

    /**
     * Partially update a fimAccounts.
     *
     * @param fimAccountsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FimAccountsDTO> partialUpdate(FimAccountsDTO fimAccountsDTO) {
        log.debug("Request to partially update FimAccounts : {}", fimAccountsDTO);

        return fimAccountsRepository
            .findById(fimAccountsDTO.getId())
            .map(existingFimAccounts -> {
                fimAccountsMapper.partialUpdate(existingFimAccounts, fimAccountsDTO);

                return existingFimAccounts;
            })
            .map(fimAccountsRepository::save)
            .map(fimAccountsMapper::toDto);
    }

    /**
     * Get all the fimAccounts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FimAccountsDTO> findAll() {
        log.debug("Request to get all FimAccounts");
        return fimAccountsRepository.findAll().stream().map(fimAccountsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fimAccounts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FimAccountsDTO> findOne(Long id) {
        log.debug("Request to get FimAccounts : {}", id);
        return fimAccountsRepository.findById(id).map(fimAccountsMapper::toDto);
    }

    /**
     * Delete the fimAccounts by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FimAccounts : {}", id);
        fimAccountsRepository.deleteById(id);
    }
}
