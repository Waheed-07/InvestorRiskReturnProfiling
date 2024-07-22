package com.scb.fimob.web.rest;

import com.scb.fimob.repository.FimAccountsWqRepository;
import com.scb.fimob.service.FimAccountsWqService;
import com.scb.fimob.service.dto.FimAccountsWqDTO;
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
 * REST controller for managing {@link com.scb.fimob.domain.FimAccountsWq}.
 */
@RestController
@RequestMapping("/api/fim-accounts-wqs")
public class FimAccountsWqResource {

    private static final Logger log = LoggerFactory.getLogger(FimAccountsWqResource.class);

    private static final String ENTITY_NAME = "fimFimAccountsWq";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FimAccountsWqService fimAccountsWqService;

    private final FimAccountsWqRepository fimAccountsWqRepository;

    public FimAccountsWqResource(FimAccountsWqService fimAccountsWqService, FimAccountsWqRepository fimAccountsWqRepository) {
        this.fimAccountsWqService = fimAccountsWqService;
        this.fimAccountsWqRepository = fimAccountsWqRepository;
    }

    /**
     * {@code POST  /fim-accounts-wqs} : Create a new fimAccountsWq.
     *
     * @param fimAccountsWqDTO the fimAccountsWqDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fimAccountsWqDTO, or with status {@code 400 (Bad Request)} if the fimAccountsWq has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FimAccountsWqDTO> createFimAccountsWq(@Valid @RequestBody FimAccountsWqDTO fimAccountsWqDTO)
        throws URISyntaxException {
        log.debug("REST request to save FimAccountsWq : {}", fimAccountsWqDTO);
        if (fimAccountsWqDTO.getId() != null) {
            throw new BadRequestAlertException("A new fimAccountsWq cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fimAccountsWqDTO = fimAccountsWqService.save(fimAccountsWqDTO);
        return ResponseEntity.created(new URI("/api/fim-accounts-wqs/" + fimAccountsWqDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fimAccountsWqDTO.getId().toString()))
            .body(fimAccountsWqDTO);
    }

    /**
     * {@code PUT  /fim-accounts-wqs/:id} : Updates an existing fimAccountsWq.
     *
     * @param id the id of the fimAccountsWqDTO to save.
     * @param fimAccountsWqDTO the fimAccountsWqDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimAccountsWqDTO,
     * or with status {@code 400 (Bad Request)} if the fimAccountsWqDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fimAccountsWqDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FimAccountsWqDTO> updateFimAccountsWq(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FimAccountsWqDTO fimAccountsWqDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FimAccountsWq : {}, {}", id, fimAccountsWqDTO);
        if (fimAccountsWqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimAccountsWqDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimAccountsWqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fimAccountsWqDTO = fimAccountsWqService.update(fimAccountsWqDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimAccountsWqDTO.getId().toString()))
            .body(fimAccountsWqDTO);
    }

    /**
     * {@code PATCH  /fim-accounts-wqs/:id} : Partial updates given fields of an existing fimAccountsWq, field will ignore if it is null
     *
     * @param id the id of the fimAccountsWqDTO to save.
     * @param fimAccountsWqDTO the fimAccountsWqDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimAccountsWqDTO,
     * or with status {@code 400 (Bad Request)} if the fimAccountsWqDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fimAccountsWqDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fimAccountsWqDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FimAccountsWqDTO> partialUpdateFimAccountsWq(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FimAccountsWqDTO fimAccountsWqDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FimAccountsWq partially : {}, {}", id, fimAccountsWqDTO);
        if (fimAccountsWqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimAccountsWqDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimAccountsWqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FimAccountsWqDTO> result = fimAccountsWqService.partialUpdate(fimAccountsWqDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimAccountsWqDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fim-accounts-wqs} : get all the fimAccountsWqs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fimAccountsWqs in body.
     */
    @GetMapping("")
    public List<FimAccountsWqDTO> getAllFimAccountsWqs() {
        log.debug("REST request to get all FimAccountsWqs");
        return fimAccountsWqService.findAll();
    }

    /**
     * {@code GET  /fim-accounts-wqs/:id} : get the "id" fimAccountsWq.
     *
     * @param id the id of the fimAccountsWqDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fimAccountsWqDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FimAccountsWqDTO> getFimAccountsWq(@PathVariable("id") Long id) {
        log.debug("REST request to get FimAccountsWq : {}", id);
        Optional<FimAccountsWqDTO> fimAccountsWqDTO = fimAccountsWqService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fimAccountsWqDTO);
    }

    /**
     * {@code DELETE  /fim-accounts-wqs/:id} : delete the "id" fimAccountsWq.
     *
     * @param id the id of the fimAccountsWqDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFimAccountsWq(@PathVariable("id") Long id) {
        log.debug("REST request to delete FimAccountsWq : {}", id);
        fimAccountsWqService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
