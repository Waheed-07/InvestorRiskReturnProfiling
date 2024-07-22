package com.scb.fimob.web.rest;

import com.scb.fimob.repository.FimSettAcctWqRepository;
import com.scb.fimob.service.FimSettAcctWqService;
import com.scb.fimob.service.dto.FimSettAcctWqDTO;
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
 * REST controller for managing {@link com.scb.fimob.domain.FimSettAcctWq}.
 */
@RestController
@RequestMapping("/api/fim-sett-acct-wqs")
public class FimSettAcctWqResource {

    private static final Logger log = LoggerFactory.getLogger(FimSettAcctWqResource.class);

    private static final String ENTITY_NAME = "fimFimSettAcctWq";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FimSettAcctWqService fimSettAcctWqService;

    private final FimSettAcctWqRepository fimSettAcctWqRepository;

    public FimSettAcctWqResource(FimSettAcctWqService fimSettAcctWqService, FimSettAcctWqRepository fimSettAcctWqRepository) {
        this.fimSettAcctWqService = fimSettAcctWqService;
        this.fimSettAcctWqRepository = fimSettAcctWqRepository;
    }

    /**
     * {@code POST  /fim-sett-acct-wqs} : Create a new fimSettAcctWq.
     *
     * @param fimSettAcctWqDTO the fimSettAcctWqDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fimSettAcctWqDTO, or with status {@code 400 (Bad Request)} if the fimSettAcctWq has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FimSettAcctWqDTO> createFimSettAcctWq(@Valid @RequestBody FimSettAcctWqDTO fimSettAcctWqDTO)
        throws URISyntaxException {
        log.debug("REST request to save FimSettAcctWq : {}", fimSettAcctWqDTO);
        if (fimSettAcctWqDTO.getId() != null) {
            throw new BadRequestAlertException("A new fimSettAcctWq cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fimSettAcctWqDTO = fimSettAcctWqService.save(fimSettAcctWqDTO);
        return ResponseEntity.created(new URI("/api/fim-sett-acct-wqs/" + fimSettAcctWqDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fimSettAcctWqDTO.getId().toString()))
            .body(fimSettAcctWqDTO);
    }

    /**
     * {@code PUT  /fim-sett-acct-wqs/:id} : Updates an existing fimSettAcctWq.
     *
     * @param id the id of the fimSettAcctWqDTO to save.
     * @param fimSettAcctWqDTO the fimSettAcctWqDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimSettAcctWqDTO,
     * or with status {@code 400 (Bad Request)} if the fimSettAcctWqDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fimSettAcctWqDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FimSettAcctWqDTO> updateFimSettAcctWq(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FimSettAcctWqDTO fimSettAcctWqDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FimSettAcctWq : {}, {}", id, fimSettAcctWqDTO);
        if (fimSettAcctWqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimSettAcctWqDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimSettAcctWqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fimSettAcctWqDTO = fimSettAcctWqService.update(fimSettAcctWqDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimSettAcctWqDTO.getId().toString()))
            .body(fimSettAcctWqDTO);
    }

    /**
     * {@code PATCH  /fim-sett-acct-wqs/:id} : Partial updates given fields of an existing fimSettAcctWq, field will ignore if it is null
     *
     * @param id the id of the fimSettAcctWqDTO to save.
     * @param fimSettAcctWqDTO the fimSettAcctWqDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimSettAcctWqDTO,
     * or with status {@code 400 (Bad Request)} if the fimSettAcctWqDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fimSettAcctWqDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fimSettAcctWqDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FimSettAcctWqDTO> partialUpdateFimSettAcctWq(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FimSettAcctWqDTO fimSettAcctWqDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FimSettAcctWq partially : {}, {}", id, fimSettAcctWqDTO);
        if (fimSettAcctWqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimSettAcctWqDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimSettAcctWqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FimSettAcctWqDTO> result = fimSettAcctWqService.partialUpdate(fimSettAcctWqDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimSettAcctWqDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fim-sett-acct-wqs} : get all the fimSettAcctWqs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fimSettAcctWqs in body.
     */
    @GetMapping("")
    public List<FimSettAcctWqDTO> getAllFimSettAcctWqs() {
        log.debug("REST request to get all FimSettAcctWqs");
        return fimSettAcctWqService.findAll();
    }

    /**
     * {@code GET  /fim-sett-acct-wqs/:id} : get the "id" fimSettAcctWq.
     *
     * @param id the id of the fimSettAcctWqDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fimSettAcctWqDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FimSettAcctWqDTO> getFimSettAcctWq(@PathVariable("id") Long id) {
        log.debug("REST request to get FimSettAcctWq : {}", id);
        Optional<FimSettAcctWqDTO> fimSettAcctWqDTO = fimSettAcctWqService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fimSettAcctWqDTO);
    }

    /**
     * {@code DELETE  /fim-sett-acct-wqs/:id} : delete the "id" fimSettAcctWq.
     *
     * @param id the id of the fimSettAcctWqDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFimSettAcctWq(@PathVariable("id") Long id) {
        log.debug("REST request to delete FimSettAcctWq : {}", id);
        fimSettAcctWqService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
