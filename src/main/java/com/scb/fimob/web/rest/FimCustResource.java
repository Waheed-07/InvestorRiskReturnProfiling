package com.scb.fimob.web.rest;

import com.scb.fimob.repository.FimCustRepository;
import com.scb.fimob.service.FimCustService;
import com.scb.fimob.service.dto.FimCustDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.scb.fimob.domain.FimCust}.
 */
@RestController
@RequestMapping("/api/fim-custs")
public class FimCustResource {

    private static final Logger log = LoggerFactory.getLogger(FimCustResource.class);

    private static final String ENTITY_NAME = "fimFimCust";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FimCustService fimCustService;

    private final FimCustRepository fimCustRepository;

    public FimCustResource(FimCustService fimCustService, FimCustRepository fimCustRepository) {
        this.fimCustService = fimCustService;
        this.fimCustRepository = fimCustRepository;
    }

    /**
     * {@code POST  /fim-custs} : Create a new fimCust.
     *
     * @param fimCustDTO the fimCustDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fimCustDTO, or with status {@code 400 (Bad Request)} if the fimCust has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FimCustDTO> createFimCust(@Valid @RequestBody FimCustDTO fimCustDTO) throws URISyntaxException {
        log.debug("REST request to save FimCust : {}", fimCustDTO);
        if (fimCustDTO.getId() != null) {
            throw new BadRequestAlertException("A new fimCust cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fimCustDTO = fimCustService.save(fimCustDTO);
        return ResponseEntity.created(new URI("/api/fim-custs/" + fimCustDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fimCustDTO.getId().toString()))
            .body(fimCustDTO);
    }

    /**
     * {@code PUT  /fim-custs/:id} : Updates an existing fimCust.
     *
     * @param id the id of the fimCustDTO to save.
     * @param fimCustDTO the fimCustDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimCustDTO,
     * or with status {@code 400 (Bad Request)} if the fimCustDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fimCustDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FimCustDTO> updateFimCust(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FimCustDTO fimCustDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FimCust : {}, {}", id, fimCustDTO);
        if (fimCustDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimCustDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimCustRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fimCustDTO = fimCustService.update(fimCustDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimCustDTO.getId().toString()))
            .body(fimCustDTO);
    }

    /**
     * {@code PATCH  /fim-custs/:id} : Partial updates given fields of an existing fimCust, field will ignore if it is null
     *
     * @param id the id of the fimCustDTO to save.
     * @param fimCustDTO the fimCustDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimCustDTO,
     * or with status {@code 400 (Bad Request)} if the fimCustDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fimCustDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fimCustDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FimCustDTO> partialUpdateFimCust(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FimCustDTO fimCustDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FimCust partially : {}, {}", id, fimCustDTO);
        if (fimCustDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimCustDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimCustRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FimCustDTO> result = fimCustService.partialUpdate(fimCustDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimCustDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fim-custs} : get all the fimCusts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fimCusts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<FimCustDTO>> getAllFimCusts(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of FimCusts");
        Page<FimCustDTO> page = fimCustService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fim-custs/:id} : get the "id" fimCust.
     *
     * @param id the id of the fimCustDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fimCustDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FimCustDTO> getFimCust(@PathVariable("id") Long id) {
        log.debug("REST request to get FimCust : {}", id);
        Optional<FimCustDTO> fimCustDTO = fimCustService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fimCustDTO);
    }

    /**
     * {@code DELETE  /fim-custs/:id} : delete the "id" fimCust.
     *
     * @param id the id of the fimCustDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFimCust(@PathVariable("id") Long id) {
        log.debug("REST request to delete FimCust : {}", id);
        fimCustService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
