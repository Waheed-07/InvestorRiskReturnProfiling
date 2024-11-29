package com.scb.fimob.web.rest;

import com.scb.fimob.repository.FimCustWqRepository;
import com.scb.fimob.service.FimCustWqService;
import com.scb.fimob.service.dto.FimCustWqDTO;
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
 * REST controller for managing {@link com.scb.fimob.domain.FimCustWq}.
 */
@RestController
@RequestMapping("/api/fim-cust-wqs")
public class FimCustWqResource {

    private static final Logger log = LoggerFactory.getLogger(FimCustWqResource.class);

    private static final String ENTITY_NAME = "fimFimCustWq";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FimCustWqService fimCustWqService;

    private final FimCustWqRepository fimCustWqRepository;

    public FimCustWqResource(FimCustWqService fimCustWqService, FimCustWqRepository fimCustWqRepository) {
        this.fimCustWqService = fimCustWqService;
        this.fimCustWqRepository = fimCustWqRepository;
    }

    /**
     * {@code POST  /fim-cust-wqs} : Create a new fimCustWq.
     *
     * @param fimCustWqDTO the fimCustWqDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fimCustWqDTO, or with status {@code 400 (Bad Request)} if the fimCustWq has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FimCustWqDTO> createFimCustWq(@Valid @RequestBody FimCustWqDTO fimCustWqDTO) throws URISyntaxException {
        log.debug("REST request to save FimCustWq : {}", fimCustWqDTO);
        if (fimCustWqDTO.getId() != null) {
            throw new BadRequestAlertException("A new fimCustWq cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fimCustWqDTO = fimCustWqService.save(fimCustWqDTO);
        return ResponseEntity.created(new URI("/api/fim-cust-wqs/" + fimCustWqDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fimCustWqDTO.getId().toString()))
            .body(fimCustWqDTO);
    }

    /**
     * {@code PUT  /fim-cust-wqs/:id} : Updates an existing fimCustWq.
     *
     * @param id the id of the fimCustWqDTO to save.
     * @param fimCustWqDTO the fimCustWqDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimCustWqDTO,
     * or with status {@code 400 (Bad Request)} if the fimCustWqDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fimCustWqDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FimCustWqDTO> updateFimCustWq(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FimCustWqDTO fimCustWqDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FimCustWq : {}, {}", id, fimCustWqDTO);
        if (fimCustWqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimCustWqDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimCustWqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fimCustWqDTO = fimCustWqService.update(fimCustWqDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimCustWqDTO.getId().toString()))
            .body(fimCustWqDTO);
    }

    /**
     * {@code PATCH  /fim-cust-wqs/:id} : Partial updates given fields of an existing fimCustWq, field will ignore if it is null
     *
     * @param id the id of the fimCustWqDTO to save.
     * @param fimCustWqDTO the fimCustWqDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimCustWqDTO,
     * or with status {@code 400 (Bad Request)} if the fimCustWqDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fimCustWqDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fimCustWqDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FimCustWqDTO> partialUpdateFimCustWq(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FimCustWqDTO fimCustWqDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FimCustWq partially : {}, {}", id, fimCustWqDTO);
        if (fimCustWqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimCustWqDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimCustWqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FimCustWqDTO> result = fimCustWqService.partialUpdate(fimCustWqDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimCustWqDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fim-cust-wqs} : get all the fimCustWqs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fimCustWqs in body.
     */
    @GetMapping("")
    public List<FimCustWqDTO> getAllFimCustWqs() {
        log.debug("REST request to get all FimCustWqs");
        return fimCustWqService.findAll();
    }

    /**
     * {@code GET  /fim-cust-wqs/:id} : get the "id" fimCustWq.
     *
     * @param id the id of the fimCustWqDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fimCustWqDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FimCustWqDTO> getFimCustWq(@PathVariable("id") Long id) {
        log.debug("REST request to get FimCustWq : {}", id);
        Optional<FimCustWqDTO> fimCustWqDTO = fimCustWqService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fimCustWqDTO);
    }

    /**
     * {@code DELETE  /fim-cust-wqs/:id} : delete the "id" fimCustWq.
     *
     * @param id the id of the fimCustWqDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFimCustWq(@PathVariable("id") Long id) {
        log.debug("REST request to delete FimCustWq : {}", id);
        fimCustWqService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
