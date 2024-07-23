package com.myapp.web.rest;

import com.myapp.repository.OptionsRepository;
import com.myapp.service.OptionsService;
import com.myapp.service.dto.OptionsDTO;
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
 * REST controller for managing {@link com.myapp.domain.Options}.
 */
@RestController
@RequestMapping("/api/options")
public class OptionsResource {

    private static final Logger log = LoggerFactory.getLogger(OptionsResource.class);

    private static final String ENTITY_NAME = "options";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OptionsService optionsService;

    private final OptionsRepository optionsRepository;

    public OptionsResource(OptionsService optionsService, OptionsRepository optionsRepository) {
        this.optionsService = optionsService;
        this.optionsRepository = optionsRepository;
    }

    /**
     * {@code POST  /options} : Create a new options.
     *
     * @param optionsDTO the optionsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new optionsDTO, or with status {@code 400 (Bad Request)} if the options has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OptionsDTO> createOptions(@Valid @RequestBody OptionsDTO optionsDTO) throws URISyntaxException {
        log.debug("REST request to save Options : {}", optionsDTO);
        if (optionsDTO.getId() != null) {
            throw new BadRequestAlertException("A new options cannot already have an ID", ENTITY_NAME, "idexists");
        }
        optionsDTO = optionsService.save(optionsDTO);
        return ResponseEntity.created(new URI("/api/options/" + optionsDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, optionsDTO.getId().toString()))
            .body(optionsDTO);
    }

    /**
     * {@code PUT  /options/:id} : Updates an existing options.
     *
     * @param id the id of the optionsDTO to save.
     * @param optionsDTO the optionsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated optionsDTO,
     * or with status {@code 400 (Bad Request)} if the optionsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the optionsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OptionsDTO> updateOptions(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OptionsDTO optionsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Options : {}, {}", id, optionsDTO);
        if (optionsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, optionsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!optionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        optionsDTO = optionsService.update(optionsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, optionsDTO.getId().toString()))
            .body(optionsDTO);
    }

    /**
     * {@code PATCH  /options/:id} : Partial updates given fields of an existing options, field will ignore if it is null
     *
     * @param id the id of the optionsDTO to save.
     * @param optionsDTO the optionsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated optionsDTO,
     * or with status {@code 400 (Bad Request)} if the optionsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the optionsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the optionsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OptionsDTO> partialUpdateOptions(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OptionsDTO optionsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Options partially : {}, {}", id, optionsDTO);
        if (optionsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, optionsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!optionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OptionsDTO> result = optionsService.partialUpdate(optionsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, optionsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /options} : get all the options.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of options in body.
     */
    @GetMapping("")
    public List<OptionsDTO> getAllOptions() {
        log.debug("REST request to get all Options");
        return optionsService.findAll();
    }

    /**
     * {@code GET  /options/:id} : get the "id" options.
     *
     * @param id the id of the optionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the optionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OptionsDTO> getOptions(@PathVariable("id") Long id) {
        log.debug("REST request to get Options : {}", id);
        Optional<OptionsDTO> optionsDTO = optionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(optionsDTO);
    }

    /**
     * {@code DELETE  /options/:id} : delete the "id" options.
     *
     * @param id the id of the optionsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOptions(@PathVariable("id") Long id) {
        log.debug("REST request to delete Options : {}", id);
        optionsService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
