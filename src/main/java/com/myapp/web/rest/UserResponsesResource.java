package com.myapp.web.rest;

import com.myapp.repository.UserResponsesRepository;
import com.myapp.service.UserResponsesService;
import com.myapp.service.dto.UserResponsesDTO;
import com.myapp.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.myapp.domain.UserResponses}.
 */
@RestController
@RequestMapping("/api/user-responses")
public class UserResponsesResource {

    private static final Logger log = LoggerFactory.getLogger(UserResponsesResource.class);

    private static final String ENTITY_NAME = "userResponses";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserResponsesService userResponsesService;

    private final UserResponsesRepository userResponsesRepository;

    public UserResponsesResource(UserResponsesService userResponsesService, UserResponsesRepository userResponsesRepository) {
        this.userResponsesService = userResponsesService;
        this.userResponsesRepository = userResponsesRepository;
    }

    /**
     * {@code POST  /user-responses} : Create a new userResponses.
     *
     * @param userResponsesDTO the userResponsesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userResponsesDTO, or with status {@code 400 (Bad Request)} if the userResponses has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<UserResponsesDTO> createUserResponses(@Valid @RequestBody UserResponsesDTO userResponsesDTO)
        throws URISyntaxException {
        log.debug("REST request to save UserResponses : {}", userResponsesDTO);
        if (userResponsesDTO.getId() != null) {
            throw new BadRequestAlertException("A new userResponses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        userResponsesDTO = userResponsesService.save(userResponsesDTO);
        return ResponseEntity.created(new URI("/api/user-responses/" + userResponsesDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, userResponsesDTO.getId().toString()))
            .body(userResponsesDTO);
    }

    /**
     * {@code PUT  /user-responses/:id} : Updates an existing userResponses.
     *
     * @param id the id of the userResponsesDTO to save.
     * @param userResponsesDTO the userResponsesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userResponsesDTO,
     * or with status {@code 400 (Bad Request)} if the userResponsesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userResponsesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponsesDTO> updateUserResponses(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody UserResponsesDTO userResponsesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserResponses : {}, {}", id, userResponsesDTO);
        if (userResponsesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userResponsesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userResponsesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        userResponsesDTO = userResponsesService.update(userResponsesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userResponsesDTO.getId().toString()))
            .body(userResponsesDTO);
    }

    /**
     * {@code PATCH  /user-responses/:id} : Partial updates given fields of an existing userResponses, field will ignore if it is null
     *
     * @param id the id of the userResponsesDTO to save.
     * @param userResponsesDTO the userResponsesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userResponsesDTO,
     * or with status {@code 400 (Bad Request)} if the userResponsesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userResponsesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userResponsesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserResponsesDTO> partialUpdateUserResponses(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody UserResponsesDTO userResponsesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserResponses partially : {}, {}", id, userResponsesDTO);
        if (userResponsesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userResponsesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userResponsesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserResponsesDTO> result = userResponsesService.partialUpdate(userResponsesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userResponsesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /user-responses} : get all the userResponses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userResponses in body.
     */
    @GetMapping("")
    public List<UserResponsesDTO> getAllUserResponses() {
        log.debug("REST request to get all UserResponses");
        return userResponsesService.findAll();
    }

    /**
     * {@code GET  /user-responses/:id} : get the "id" userResponses.
     *
     * @param id the id of the userResponsesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userResponsesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponsesDTO> getUserResponses(@PathVariable("id") Long id) {
        log.debug("REST request to get UserResponses : {}", id);
        Optional<UserResponsesDTO> userResponsesDTO = userResponsesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userResponsesDTO);
    }

    /**
     * {@code DELETE  /user-responses/:id} : delete the "id" userResponses.
     *
     * @param id the id of the userResponsesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserResponses(@PathVariable("id") Long id) {
        log.debug("REST request to delete UserResponses : {}", id);
        userResponsesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
