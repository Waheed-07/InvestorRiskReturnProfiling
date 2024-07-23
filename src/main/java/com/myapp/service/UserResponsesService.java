package com.myapp.service;

import com.myapp.domain.UserResponses;
import com.myapp.repository.UserResponsesRepository;
import com.myapp.service.dto.UserResponsesDTO;
import com.myapp.service.mapper.UserResponsesMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.myapp.domain.UserResponses}.
 */
@Service
@Transactional
public class UserResponsesService {

    private static final Logger log = LoggerFactory.getLogger(UserResponsesService.class);

    private final UserResponsesRepository userResponsesRepository;

    private final UserResponsesMapper userResponsesMapper;

    public UserResponsesService(UserResponsesRepository userResponsesRepository, UserResponsesMapper userResponsesMapper) {
        this.userResponsesRepository = userResponsesRepository;
        this.userResponsesMapper = userResponsesMapper;
    }

    /**
     * Save a userResponses.
     *
     * @param userResponsesDTO the entity to save.
     * @return the persisted entity.
     */
    public UserResponsesDTO save(UserResponsesDTO userResponsesDTO) {
        log.debug("Request to save UserResponses : {}", userResponsesDTO);
        UserResponses userResponses = userResponsesMapper.toEntity(userResponsesDTO);
        userResponses = userResponsesRepository.save(userResponses);
        return userResponsesMapper.toDto(userResponses);
    }

    /**
     * Update a userResponses.
     *
     * @param userResponsesDTO the entity to save.
     * @return the persisted entity.
     */
    public UserResponsesDTO update(UserResponsesDTO userResponsesDTO) {
        log.debug("Request to update UserResponses : {}", userResponsesDTO);
        UserResponses userResponses = userResponsesMapper.toEntity(userResponsesDTO);
        userResponses = userResponsesRepository.save(userResponses);
        return userResponsesMapper.toDto(userResponses);
    }

    /**
     * Partially update a userResponses.
     *
     * @param userResponsesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserResponsesDTO> partialUpdate(UserResponsesDTO userResponsesDTO) {
        log.debug("Request to partially update UserResponses : {}", userResponsesDTO);

        return userResponsesRepository
            .findById(userResponsesDTO.getId())
            .map(existingUserResponses -> {
                userResponsesMapper.partialUpdate(existingUserResponses, userResponsesDTO);

                return existingUserResponses;
            })
            .map(userResponsesRepository::save)
            .map(userResponsesMapper::toDto);
    }

    /**
     * Get all the userResponses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserResponsesDTO> findAll() {
        log.debug("Request to get all UserResponses");
        return userResponsesRepository.findAll().stream().map(userResponsesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one userResponses by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserResponsesDTO> findOne(Long id) {
        log.debug("Request to get UserResponses : {}", id);
        return userResponsesRepository.findById(id).map(userResponsesMapper::toDto);
    }

    /**
     * Delete the userResponses by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserResponses : {}", id);
        userResponsesRepository.deleteById(id);
    }
}
