package com.scb.fimob.web.rest;

import com.scb.fimob.repository.FimSettAcctHistoryRepository;
import com.scb.fimob.service.FimSettAcctHistoryService;
import com.scb.fimob.service.dto.FimSettAcctHistoryDTO;
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
 * REST controller for managing {@link com.scb.fimob.domain.FimSettAcctHistory}.
 */
@RestController
@RequestMapping("/api/fim-sett-acct-histories")
public class FimSettAcctHistoryResource {

    private static final Logger log = LoggerFactory.getLogger(FimSettAcctHistoryResource.class);

    private static final String ENTITY_NAME = "fimFimSettAcctHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FimSettAcctHistoryService fimSettAcctHistoryService;

    private final FimSettAcctHistoryRepository fimSettAcctHistoryRepository;

    public FimSettAcctHistoryResource(
        FimSettAcctHistoryService fimSettAcctHistoryService,
        FimSettAcctHistoryRepository fimSettAcctHistoryRepository
    ) {
        this.fimSettAcctHistoryService = fimSettAcctHistoryService;
        this.fimSettAcctHistoryRepository = fimSettAcctHistoryRepository;
    }

    /**
     * {@code POST  /fim-sett-acct-histories} : Create a new fimSettAcctHistory.
     *
     * @param fimSettAcctHistoryDTO the fimSettAcctHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fimSettAcctHistoryDTO, or with status {@code 400 (Bad Request)} if the fimSettAcctHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FimSettAcctHistoryDTO> createFimSettAcctHistory(@Valid @RequestBody FimSettAcctHistoryDTO fimSettAcctHistoryDTO)
        throws URISyntaxException {
        log.debug("REST request to save FimSettAcctHistory : {}", fimSettAcctHistoryDTO);
        if (fimSettAcctHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new fimSettAcctHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fimSettAcctHistoryDTO = fimSettAcctHistoryService.save(fimSettAcctHistoryDTO);
        return ResponseEntity.created(new URI("/api/fim-sett-acct-histories/" + fimSettAcctHistoryDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fimSettAcctHistoryDTO.getId().toString()))
            .body(fimSettAcctHistoryDTO);
    }

    /**
     * {@code PUT  /fim-sett-acct-histories/:id} : Updates an existing fimSettAcctHistory.
     *
     * @param id the id of the fimSettAcctHistoryDTO to save.
     * @param fimSettAcctHistoryDTO the fimSettAcctHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimSettAcctHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the fimSettAcctHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fimSettAcctHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FimSettAcctHistoryDTO> updateFimSettAcctHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FimSettAcctHistoryDTO fimSettAcctHistoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FimSettAcctHistory : {}, {}", id, fimSettAcctHistoryDTO);
        if (fimSettAcctHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimSettAcctHistoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimSettAcctHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fimSettAcctHistoryDTO = fimSettAcctHistoryService.update(fimSettAcctHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimSettAcctHistoryDTO.getId().toString()))
            .body(fimSettAcctHistoryDTO);
    }

    /**
     * {@code PATCH  /fim-sett-acct-histories/:id} : Partial updates given fields of an existing fimSettAcctHistory, field will ignore if it is null
     *
     * @param id the id of the fimSettAcctHistoryDTO to save.
     * @param fimSettAcctHistoryDTO the fimSettAcctHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimSettAcctHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the fimSettAcctHistoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fimSettAcctHistoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fimSettAcctHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FimSettAcctHistoryDTO> partialUpdateFimSettAcctHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FimSettAcctHistoryDTO fimSettAcctHistoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FimSettAcctHistory partially : {}, {}", id, fimSettAcctHistoryDTO);
        if (fimSettAcctHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimSettAcctHistoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimSettAcctHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FimSettAcctHistoryDTO> result = fimSettAcctHistoryService.partialUpdate(fimSettAcctHistoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimSettAcctHistoryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fim-sett-acct-histories} : get all the fimSettAcctHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fimSettAcctHistories in body.
     */
    @GetMapping("")
    public List<FimSettAcctHistoryDTO> getAllFimSettAcctHistories() {
        log.debug("REST request to get all FimSettAcctHistories");
        return fimSettAcctHistoryService.findAll();
    }

    /**
     * {@code GET  /fim-sett-acct-histories/:id} : get the "id" fimSettAcctHistory.
     *
     * @param id the id of the fimSettAcctHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fimSettAcctHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FimSettAcctHistoryDTO> getFimSettAcctHistory(@PathVariable("id") Long id) {
        log.debug("REST request to get FimSettAcctHistory : {}", id);
        Optional<FimSettAcctHistoryDTO> fimSettAcctHistoryDTO = fimSettAcctHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fimSettAcctHistoryDTO);
    }

    /**
     * {@code DELETE  /fim-sett-acct-histories/:id} : delete the "id" fimSettAcctHistory.
     *
     * @param id the id of the fimSettAcctHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFimSettAcctHistory(@PathVariable("id") Long id) {
        log.debug("REST request to delete FimSettAcctHistory : {}", id);
        fimSettAcctHistoryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
