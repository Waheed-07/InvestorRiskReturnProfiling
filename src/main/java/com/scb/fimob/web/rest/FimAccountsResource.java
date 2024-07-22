package com.scb.fimob.web.rest;

import com.scb.fimob.repository.FimAccountsRepository;
import com.scb.fimob.service.FimAccountsService;
import com.scb.fimob.service.dto.FimAccountsDTO;
import com.scb.fimob.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.scb.fimob.domain.FimAccounts}.
 */
@RestController
@RequestMapping("/api/fim-accounts")
public class FimAccountsResource {

    private static final Logger log = LoggerFactory.getLogger(FimAccountsResource.class);

    private static final String ENTITY_NAME = "fimFimAccounts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FimAccountsService fimAccountsService;

    private final FimAccountsRepository fimAccountsRepository;

    public FimAccountsResource(FimAccountsService fimAccountsService, FimAccountsRepository fimAccountsRepository) {
        this.fimAccountsService = fimAccountsService;
        this.fimAccountsRepository = fimAccountsRepository;
    }

    /**
     * {@code POST  /fim-accounts} : Create a new fimAccounts.
     *
     * @param fimAccountsDTO the fimAccountsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fimAccountsDTO, or with status {@code 400 (Bad Request)} if the fimAccounts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FimAccountsDTO> createFimAccounts(@Valid @RequestBody FimAccountsDTO fimAccountsDTO) throws URISyntaxException {
        log.debug("REST request to save FimAccounts : {}", fimAccountsDTO);
        if (fimAccountsDTO.getId() != null) {
            throw new BadRequestAlertException("A new fimAccounts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fimAccountsDTO = fimAccountsService.save(fimAccountsDTO);
        return ResponseEntity.created(new URI("/api/fim-accounts/" + fimAccountsDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fimAccountsDTO.getId().toString()))
            .body(fimAccountsDTO);
    }

    /**
     * {@code PUT  /fim-accounts/:id} : Updates an existing fimAccounts.
     *
     * @param id the id of the fimAccountsDTO to save.
     * @param fimAccountsDTO the fimAccountsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimAccountsDTO,
     * or with status {@code 400 (Bad Request)} if the fimAccountsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fimAccountsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FimAccountsDTO> updateFimAccounts(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FimAccountsDTO fimAccountsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FimAccounts : {}, {}", id, fimAccountsDTO);
        if (fimAccountsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimAccountsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimAccountsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fimAccountsDTO = fimAccountsService.update(fimAccountsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimAccountsDTO.getId().toString()))
            .body(fimAccountsDTO);
    }

    /**
     * {@code PATCH  /fim-accounts/:id} : Partial updates given fields of an existing fimAccounts, field will ignore if it is null
     *
     * @param id the id of the fimAccountsDTO to save.
     * @param fimAccountsDTO the fimAccountsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimAccountsDTO,
     * or with status {@code 400 (Bad Request)} if the fimAccountsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fimAccountsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fimAccountsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FimAccountsDTO> partialUpdateFimAccounts(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FimAccountsDTO fimAccountsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FimAccounts partially : {}, {}", id, fimAccountsDTO);
        if (fimAccountsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimAccountsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimAccountsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FimAccountsDTO> result = fimAccountsService.partialUpdate(fimAccountsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimAccountsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fim-accounts} : get all the fimAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fimAccounts in body.
     */
    @GetMapping("")
    public List<FimAccountsDTO> getAllFimAccounts() {
        log.debug("REST request to get all FimAccounts");
        return fimAccountsService.findAll();
    }

    /**
     * {@code GET  /fim-accounts/:id} : get the "id" fimAccounts.
     *
     * @param id the id of the fimAccountsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fimAccountsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FimAccountsDTO> getFimAccounts(@PathVariable("id") Long id) {
        log.debug("REST request to get FimAccounts : {}", id);
        Optional<FimAccountsDTO> fimAccountsDTO = fimAccountsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fimAccountsDTO);
    }

    /**
     * {@code DELETE  /fim-accounts/:id} : delete the "id" fimAccounts.
     *
     * @param id the id of the fimAccountsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFimAccounts(@PathVariable("id") Long id) {
        log.debug("REST request to delete FimAccounts : {}", id);
        fimAccountsService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
