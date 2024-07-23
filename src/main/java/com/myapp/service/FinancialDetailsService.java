package com.myapp.service;

import com.myapp.domain.FinancialDetails;
import com.myapp.repository.FinancialDetailsRepository;
import com.myapp.service.dto.FinancialDetailsDTO;
import com.myapp.service.mapper.FinancialDetailsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.myapp.domain.FinancialDetails}.
 */
@Service
@Transactional
public class FinancialDetailsService {

    private static final Logger log = LoggerFactory.getLogger(FinancialDetailsService.class);

    private final FinancialDetailsRepository financialDetailsRepository;

    private final FinancialDetailsMapper financialDetailsMapper;

    public FinancialDetailsService(FinancialDetailsRepository financialDetailsRepository, FinancialDetailsMapper financialDetailsMapper) {
        this.financialDetailsRepository = financialDetailsRepository;
        this.financialDetailsMapper = financialDetailsMapper;
    }

    /**
     * Save a financialDetails.
     *
     * @param financialDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public FinancialDetailsDTO save(FinancialDetailsDTO financialDetailsDTO) {
        log.debug("Request to save FinancialDetails : {}", financialDetailsDTO);
        FinancialDetails financialDetails = financialDetailsMapper.toEntity(financialDetailsDTO);
        financialDetails = financialDetailsRepository.save(financialDetails);
        return financialDetailsMapper.toDto(financialDetails);
    }

    /**
     * Update a financialDetails.
     *
     * @param financialDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public FinancialDetailsDTO update(FinancialDetailsDTO financialDetailsDTO) {
        log.debug("Request to update FinancialDetails : {}", financialDetailsDTO);
        FinancialDetails financialDetails = financialDetailsMapper.toEntity(financialDetailsDTO);
        financialDetails = financialDetailsRepository.save(financialDetails);
        return financialDetailsMapper.toDto(financialDetails);
    }

    /**
     * Partially update a financialDetails.
     *
     * @param financialDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FinancialDetailsDTO> partialUpdate(FinancialDetailsDTO financialDetailsDTO) {
        log.debug("Request to partially update FinancialDetails : {}", financialDetailsDTO);

        return financialDetailsRepository
            .findById(financialDetailsDTO.getId())
            .map(existingFinancialDetails -> {
                financialDetailsMapper.partialUpdate(existingFinancialDetails, financialDetailsDTO);

                return existingFinancialDetails;
            })
            .map(financialDetailsRepository::save)
            .map(financialDetailsMapper::toDto);
    }

    /**
     * Get all the financialDetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FinancialDetailsDTO> findAll() {
        log.debug("Request to get all FinancialDetails");
        return financialDetailsRepository
            .findAll()
            .stream()
            .map(financialDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one financialDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FinancialDetailsDTO> findOne(Long id) {
        log.debug("Request to get FinancialDetails : {}", id);
        return financialDetailsRepository.findById(id).map(financialDetailsMapper::toDto);
    }

    /**
     * Delete the financialDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FinancialDetails : {}", id);
        financialDetailsRepository.deleteById(id);
    }
}
