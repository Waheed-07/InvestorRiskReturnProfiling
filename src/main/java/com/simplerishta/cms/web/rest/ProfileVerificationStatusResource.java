package com.simplerishta.cms.web.rest;

import com.simplerishta.cms.repository.ProfileVerificationStatusRepository;
import com.simplerishta.cms.service.ProfileVerificationStatusService;
import com.simplerishta.cms.service.dto.ProfileVerificationStatusDTO;
import com.simplerishta.cms.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.simplerishta.cms.domain.ProfileVerificationStatus}.
 */
@RestController
@RequestMapping("/api/profile-verification-statuses")
public class ProfileVerificationStatusResource {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileVerificationStatusResource.class);

    private static final String ENTITY_NAME = "simpleRishtaProfileVerificationStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfileVerificationStatusService profileVerificationStatusService;

    private final ProfileVerificationStatusRepository profileVerificationStatusRepository;

    public ProfileVerificationStatusResource(
        ProfileVerificationStatusService profileVerificationStatusService,
        ProfileVerificationStatusRepository profileVerificationStatusRepository
    ) {
        this.profileVerificationStatusService = profileVerificationStatusService;
        this.profileVerificationStatusRepository = profileVerificationStatusRepository;
    }

    /**
     * {@code POST  /profile-verification-statuses} : Create a new profileVerificationStatus.
     *
     * @param profileVerificationStatusDTO the profileVerificationStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profileVerificationStatusDTO, or with status {@code 400 (Bad Request)} if the profileVerificationStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProfileVerificationStatusDTO> createProfileVerificationStatus(
        @Valid @RequestBody ProfileVerificationStatusDTO profileVerificationStatusDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save ProfileVerificationStatus : {}", profileVerificationStatusDTO);
        if (profileVerificationStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new profileVerificationStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        profileVerificationStatusDTO = profileVerificationStatusService.save(profileVerificationStatusDTO);
        return ResponseEntity.created(new URI("/api/profile-verification-statuses/" + profileVerificationStatusDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, profileVerificationStatusDTO.getId().toString())
            )
            .body(profileVerificationStatusDTO);
    }

    /**
     * {@code PUT  /profile-verification-statuses/:id} : Updates an existing profileVerificationStatus.
     *
     * @param id the id of the profileVerificationStatusDTO to save.
     * @param profileVerificationStatusDTO the profileVerificationStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profileVerificationStatusDTO,
     * or with status {@code 400 (Bad Request)} if the profileVerificationStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profileVerificationStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProfileVerificationStatusDTO> updateProfileVerificationStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProfileVerificationStatusDTO profileVerificationStatusDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ProfileVerificationStatus : {}, {}", id, profileVerificationStatusDTO);
        if (profileVerificationStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, profileVerificationStatusDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!profileVerificationStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        profileVerificationStatusDTO = profileVerificationStatusService.update(profileVerificationStatusDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, profileVerificationStatusDTO.getId().toString())
            )
            .body(profileVerificationStatusDTO);
    }

    /**
     * {@code PATCH  /profile-verification-statuses/:id} : Partial updates given fields of an existing profileVerificationStatus, field will ignore if it is null
     *
     * @param id the id of the profileVerificationStatusDTO to save.
     * @param profileVerificationStatusDTO the profileVerificationStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profileVerificationStatusDTO,
     * or with status {@code 400 (Bad Request)} if the profileVerificationStatusDTO is not valid,
     * or with status {@code 404 (Not Found)} if the profileVerificationStatusDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the profileVerificationStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProfileVerificationStatusDTO> partialUpdateProfileVerificationStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProfileVerificationStatusDTO profileVerificationStatusDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ProfileVerificationStatus partially : {}, {}", id, profileVerificationStatusDTO);
        if (profileVerificationStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, profileVerificationStatusDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!profileVerificationStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProfileVerificationStatusDTO> result = profileVerificationStatusService.partialUpdate(profileVerificationStatusDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, profileVerificationStatusDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /profile-verification-statuses} : get all the profileVerificationStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profileVerificationStatuses in body.
     */
    @GetMapping("")
    public List<ProfileVerificationStatusDTO> getAllProfileVerificationStatuses() {
        LOG.debug("REST request to get all ProfileVerificationStatuses");
        return profileVerificationStatusService.findAll();
    }

    /**
     * {@code GET  /profile-verification-statuses/:id} : get the "id" profileVerificationStatus.
     *
     * @param id the id of the profileVerificationStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profileVerificationStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfileVerificationStatusDTO> getProfileVerificationStatus(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ProfileVerificationStatus : {}", id);
        Optional<ProfileVerificationStatusDTO> profileVerificationStatusDTO = profileVerificationStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profileVerificationStatusDTO);
    }

    /**
     * {@code DELETE  /profile-verification-statuses/:id} : delete the "id" profileVerificationStatus.
     *
     * @param id the id of the profileVerificationStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfileVerificationStatus(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ProfileVerificationStatus : {}", id);
        profileVerificationStatusService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
