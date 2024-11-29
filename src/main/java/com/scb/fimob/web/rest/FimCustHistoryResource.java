package com.scb.fimob.web.rest;

import com.scb.fimob.repository.FimCustHistoryRepository;
import com.scb.fimob.service.FimCustHistoryService;
import com.scb.fimob.service.dto.FimCustHistoryDTO;
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
 * REST controller for managing {@link com.scb.fimob.domain.FimCustHistory}.
 */
@RestController
@RequestMapping("/api/fim-cust-histories")
public class FimCustHistoryResource {

    private static final Logger log = LoggerFactory.getLogger(FimCustHistoryResource.class);

    private static final String ENTITY_NAME = "fimFimCustHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FimCustHistoryService fimCustHistoryService;

    private final FimCustHistoryRepository fimCustHistoryRepository;

    public FimCustHistoryResource(FimCustHistoryService fimCustHistoryService, FimCustHistoryRepository fimCustHistoryRepository) {
        this.fimCustHistoryService = fimCustHistoryService;
        this.fimCustHistoryRepository = fimCustHistoryRepository;
    }

    /**
     * {@code POST  /fim-cust-histories} : Create a new fimCustHistory.
     *
     * @param fimCustHistoryDTO the fimCustHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fimCustHistoryDTO, or with status {@code 400 (Bad Request)} if the fimCustHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FimCustHistoryDTO> createFimCustHistory(@Valid @RequestBody FimCustHistoryDTO fimCustHistoryDTO)
        throws URISyntaxException {
        log.debug("REST request to save FimCustHistory : {}", fimCustHistoryDTO);
        if (fimCustHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new fimCustHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fimCustHistoryDTO = fimCustHistoryService.save(fimCustHistoryDTO);
        return ResponseEntity.created(new URI("/api/fim-cust-histories/" + fimCustHistoryDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fimCustHistoryDTO.getId().toString()))
            .body(fimCustHistoryDTO);
    }

    /**
     * {@code PUT  /fim-cust-histories/:id} : Updates an existing fimCustHistory.
     *
     * @param id the id of the fimCustHistoryDTO to save.
     * @param fimCustHistoryDTO the fimCustHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimCustHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the fimCustHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fimCustHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FimCustHistoryDTO> updateFimCustHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FimCustHistoryDTO fimCustHistoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FimCustHistory : {}, {}", id, fimCustHistoryDTO);
        if (fimCustHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimCustHistoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimCustHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fimCustHistoryDTO = fimCustHistoryService.update(fimCustHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimCustHistoryDTO.getId().toString()))
            .body(fimCustHistoryDTO);
    }

    /**
     * {@code PATCH  /fim-cust-histories/:id} : Partial updates given fields of an existing fimCustHistory, field will ignore if it is null
     *
     * @param id the id of the fimCustHistoryDTO to save.
     * @param fimCustHistoryDTO the fimCustHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimCustHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the fimCustHistoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fimCustHistoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fimCustHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FimCustHistoryDTO> partialUpdateFimCustHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FimCustHistoryDTO fimCustHistoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FimCustHistory partially : {}, {}", id, fimCustHistoryDTO);
        if (fimCustHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimCustHistoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimCustHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FimCustHistoryDTO> result = fimCustHistoryService.partialUpdate(fimCustHistoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimCustHistoryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fim-cust-histories} : get all the fimCustHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fimCustHistories in body.
     */
    @GetMapping("")
    public List<FimCustHistoryDTO> getAllFimCustHistories() {
        log.debug("REST request to get all FimCustHistories");
        return fimCustHistoryService.findAll();
    }

    /**
     * {@code GET  /fim-cust-histories/:id} : get the "id" fimCustHistory.
     *
     * @param id the id of the fimCustHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fimCustHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FimCustHistoryDTO> getFimCustHistory(@PathVariable("id") Long id) {
        log.debug("REST request to get FimCustHistory : {}", id);
        Optional<FimCustHistoryDTO> fimCustHistoryDTO = fimCustHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fimCustHistoryDTO);
    }

    /**
     * {@code DELETE  /fim-cust-histories/:id} : delete the "id" fimCustHistory.
     *
     * @param id the id of the fimCustHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFimCustHistory(@PathVariable("id") Long id) {
        log.debug("REST request to delete FimCustHistory : {}", id);
        fimCustHistoryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
