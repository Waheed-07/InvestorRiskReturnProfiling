package com.myapp.web.rest;

import com.myapp.domain.PersonalDetails;
import com.myapp.repository.PersonalDetailsRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.myapp.domain.PersonalDetails}.
 */
@RestController
@RequestMapping("/api/personal-details")
@Transactional
public class PersonalDetailsResource {

    private static final Logger log = LoggerFactory.getLogger(PersonalDetailsResource.class);

    private static final String ENTITY_NAME = "personalDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonalDetailsRepository personalDetailsRepository;

    public PersonalDetailsResource(PersonalDetailsRepository personalDetailsRepository) {
        this.personalDetailsRepository = personalDetailsRepository;
    }

    /**
     * {@code POST  /personal-details} : Create a new personalDetails.
     *
     * @param personalDetails the personalDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personalDetails, or with status {@code 400 (Bad Request)} if the personalDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PersonalDetails> createPersonalDetails(@Valid @RequestBody PersonalDetails personalDetails)
        throws URISyntaxException {
        log.debug("REST request to save PersonalDetails : {}", personalDetails);
        if (personalDetails.getId() != null) {
            throw new BadRequestAlertException("A new personalDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        personalDetails = personalDetailsRepository.save(personalDetails);
        return ResponseEntity.created(new URI("/api/personal-details/" + personalDetails.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, personalDetails.getId().toString()))
            .body(personalDetails);
    }

    /**
     * {@code PUT  /personal-details/:id} : Updates an existing personalDetails.
     *
     * @param id the id of the personalDetails to save.
     * @param personalDetails the personalDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personalDetails,
     * or with status {@code 400 (Bad Request)} if the personalDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personalDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PersonalDetails> updatePersonalDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PersonalDetails personalDetails
    ) throws URISyntaxException {
        log.debug("REST request to update PersonalDetails : {}, {}", id, personalDetails);
        if (personalDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, personalDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personalDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        personalDetails = personalDetailsRepository.save(personalDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personalDetails.getId().toString()))
            .body(personalDetails);
    }

    /**
     * {@code PATCH  /personal-details/:id} : Partial updates given fields of an existing personalDetails, field will ignore if it is null
     *
     * @param id the id of the personalDetails to save.
     * @param personalDetails the personalDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personalDetails,
     * or with status {@code 400 (Bad Request)} if the personalDetails is not valid,
     * or with status {@code 404 (Not Found)} if the personalDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the personalDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PersonalDetails> partialUpdatePersonalDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PersonalDetails personalDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update PersonalDetails partially : {}, {}", id, personalDetails);
        if (personalDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, personalDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personalDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PersonalDetails> result = personalDetailsRepository
            .findById(personalDetails.getId())
            .map(existingPersonalDetails -> {
                if (personalDetails.getFullName() != null) {
                    existingPersonalDetails.setFullName(personalDetails.getFullName());
                }
                if (personalDetails.getDateOfBirth() != null) {
                    existingPersonalDetails.setDateOfBirth(personalDetails.getDateOfBirth());
                }
                if (personalDetails.getCreatedAt() != null) {
                    existingPersonalDetails.setCreatedAt(personalDetails.getCreatedAt());
                }
                if (personalDetails.getUpdatedAt() != null) {
                    existingPersonalDetails.setUpdatedAt(personalDetails.getUpdatedAt());
                }

                return existingPersonalDetails;
            })
            .map(personalDetailsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personalDetails.getId().toString())
        );
    }

    /**
     * {@code GET  /personal-details} : get all the personalDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personalDetails in body.
     */
    @GetMapping("")
    public List<PersonalDetails> getAllPersonalDetails() {
        log.debug("REST request to get all PersonalDetails");
        return personalDetailsRepository.findAll();
    }

    /**
     * {@code GET  /personal-details/:id} : get the "id" personalDetails.
     *
     * @param id the id of the personalDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personalDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonalDetails> getPersonalDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get PersonalDetails : {}", id);
        Optional<PersonalDetails> personalDetails = personalDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(personalDetails);
    }

    /**
     * {@code DELETE  /personal-details/:id} : delete the "id" personalDetails.
     *
     * @param id the id of the personalDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete PersonalDetails : {}", id);
        personalDetailsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
