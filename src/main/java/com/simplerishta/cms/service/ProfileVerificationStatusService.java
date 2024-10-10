package com.simplerishta.cms.service;

import com.simplerishta.cms.domain.ProfileVerificationStatus;
import com.simplerishta.cms.repository.ProfileVerificationStatusRepository;
import com.simplerishta.cms.service.dto.ProfileVerificationStatusDTO;
import com.simplerishta.cms.service.mapper.ProfileVerificationStatusMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.simplerishta.cms.domain.ProfileVerificationStatus}.
 */
@Service
@Transactional
public class ProfileVerificationStatusService {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileVerificationStatusService.class);

    private final ProfileVerificationStatusRepository profileVerificationStatusRepository;

    private final ProfileVerificationStatusMapper profileVerificationStatusMapper;

    public ProfileVerificationStatusService(
        ProfileVerificationStatusRepository profileVerificationStatusRepository,
        ProfileVerificationStatusMapper profileVerificationStatusMapper
    ) {
        this.profileVerificationStatusRepository = profileVerificationStatusRepository;
        this.profileVerificationStatusMapper = profileVerificationStatusMapper;
    }

    /**
     * Save a profileVerificationStatus.
     *
     * @param profileVerificationStatusDTO the entity to save.
     * @return the persisted entity.
     */
    public ProfileVerificationStatusDTO save(ProfileVerificationStatusDTO profileVerificationStatusDTO) {
        LOG.debug("Request to save ProfileVerificationStatus : {}", profileVerificationStatusDTO);
        ProfileVerificationStatus profileVerificationStatus = profileVerificationStatusMapper.toEntity(profileVerificationStatusDTO);
        profileVerificationStatus = profileVerificationStatusRepository.save(profileVerificationStatus);
        return profileVerificationStatusMapper.toDto(profileVerificationStatus);
    }

    /**
     * Update a profileVerificationStatus.
     *
     * @param profileVerificationStatusDTO the entity to save.
     * @return the persisted entity.
     */
    public ProfileVerificationStatusDTO update(ProfileVerificationStatusDTO profileVerificationStatusDTO) {
        LOG.debug("Request to update ProfileVerificationStatus : {}", profileVerificationStatusDTO);
        ProfileVerificationStatus profileVerificationStatus = profileVerificationStatusMapper.toEntity(profileVerificationStatusDTO);
        profileVerificationStatus = profileVerificationStatusRepository.save(profileVerificationStatus);
        return profileVerificationStatusMapper.toDto(profileVerificationStatus);
    }

    /**
     * Partially update a profileVerificationStatus.
     *
     * @param profileVerificationStatusDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProfileVerificationStatusDTO> partialUpdate(ProfileVerificationStatusDTO profileVerificationStatusDTO) {
        LOG.debug("Request to partially update ProfileVerificationStatus : {}", profileVerificationStatusDTO);

        return profileVerificationStatusRepository
            .findById(profileVerificationStatusDTO.getId())
            .map(existingProfileVerificationStatus -> {
                profileVerificationStatusMapper.partialUpdate(existingProfileVerificationStatus, profileVerificationStatusDTO);

                return existingProfileVerificationStatus;
            })
            .map(profileVerificationStatusRepository::save)
            .map(profileVerificationStatusMapper::toDto);
    }

    /**
     * Get all the profileVerificationStatuses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProfileVerificationStatusDTO> findAll() {
        LOG.debug("Request to get all ProfileVerificationStatuses");
        return profileVerificationStatusRepository
            .findAll()
            .stream()
            .map(profileVerificationStatusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one profileVerificationStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProfileVerificationStatusDTO> findOne(Long id) {
        LOG.debug("Request to get ProfileVerificationStatus : {}", id);
        return profileVerificationStatusRepository.findById(id).map(profileVerificationStatusMapper::toDto);
    }

    /**
     * Delete the profileVerificationStatus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete ProfileVerificationStatus : {}", id);
        profileVerificationStatusRepository.deleteById(id);
    }
}
