package com.scb.fimob.web.rest;

import com.scb.fimob.repository.FimSettAcctRepository;
import com.scb.fimob.service.FimSettAcctService;
import com.scb.fimob.service.dto.FimSettAcctDTO;
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
 * REST controller for managing {@link com.scb.fimob.domain.FimSettAcct}.
 */
@RestController
@RequestMapping("/api/fim-sett-accts")
public class FimSettAcctResource {

    private static final Logger log = LoggerFactory.getLogger(FimSettAcctResource.class);

    private static final String ENTITY_NAME = "fimFimSettAcct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FimSettAcctService fimSettAcctService;

    private final FimSettAcctRepository fimSettAcctRepository;

    public FimSettAcctResource(FimSettAcctService fimSettAcctService, FimSettAcctRepository fimSettAcctRepository) {
        this.fimSettAcctService = fimSettAcctService;
        this.fimSettAcctRepository = fimSettAcctRepository;
    }

    /**
     * {@code POST  /fim-sett-accts} : Create a new fimSettAcct.
     *
     * @param fimSettAcctDTO the fimSettAcctDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fimSettAcctDTO, or with status {@code 400 (Bad Request)} if the fimSettAcct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FimSettAcctDTO> createFimSettAcct(@Valid @RequestBody FimSettAcctDTO fimSettAcctDTO) throws URISyntaxException {
        log.debug("REST request to save FimSettAcct : {}", fimSettAcctDTO);
        if (fimSettAcctDTO.getId() != null) {
            throw new BadRequestAlertException("A new fimSettAcct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fimSettAcctDTO = fimSettAcctService.save(fimSettAcctDTO);
        return ResponseEntity.created(new URI("/api/fim-sett-accts/" + fimSettAcctDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fimSettAcctDTO.getId().toString()))
            .body(fimSettAcctDTO);
    }

    /**
     * {@code PUT  /fim-sett-accts/:id} : Updates an existing fimSettAcct.
     *
     * @param id the id of the fimSettAcctDTO to save.
     * @param fimSettAcctDTO the fimSettAcctDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimSettAcctDTO,
     * or with status {@code 400 (Bad Request)} if the fimSettAcctDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fimSettAcctDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FimSettAcctDTO> updateFimSettAcct(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FimSettAcctDTO fimSettAcctDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FimSettAcct : {}, {}", id, fimSettAcctDTO);
        if (fimSettAcctDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimSettAcctDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimSettAcctRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fimSettAcctDTO = fimSettAcctService.update(fimSettAcctDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimSettAcctDTO.getId().toString()))
            .body(fimSettAcctDTO);
    }

    /**
     * {@code PATCH  /fim-sett-accts/:id} : Partial updates given fields of an existing fimSettAcct, field will ignore if it is null
     *
     * @param id the id of the fimSettAcctDTO to save.
     * @param fimSettAcctDTO the fimSettAcctDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fimSettAcctDTO,
     * or with status {@code 400 (Bad Request)} if the fimSettAcctDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fimSettAcctDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fimSettAcctDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FimSettAcctDTO> partialUpdateFimSettAcct(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FimSettAcctDTO fimSettAcctDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FimSettAcct partially : {}, {}", id, fimSettAcctDTO);
        if (fimSettAcctDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fimSettAcctDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fimSettAcctRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FimSettAcctDTO> result = fimSettAcctService.partialUpdate(fimSettAcctDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fimSettAcctDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fim-sett-accts} : get all the fimSettAccts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fimSettAccts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<FimSettAcctDTO>> getAllFimSettAccts(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of FimSettAccts");
        Page<FimSettAcctDTO> page = fimSettAcctService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fim-sett-accts/:id} : get the "id" fimSettAcct.
     *
     * @param id the id of the fimSettAcctDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fimSettAcctDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FimSettAcctDTO> getFimSettAcct(@PathVariable("id") Long id) {
        log.debug("REST request to get FimSettAcct : {}", id);
        Optional<FimSettAcctDTO> fimSettAcctDTO = fimSettAcctService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fimSettAcctDTO);
    }

    /**
     * {@code DELETE  /fim-sett-accts/:id} : delete the "id" fimSettAcct.
     *
     * @param id the id of the fimSettAcctDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFimSettAcct(@PathVariable("id") Long id) {
        log.debug("REST request to delete FimSettAcct : {}", id);
        fimSettAcctService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
