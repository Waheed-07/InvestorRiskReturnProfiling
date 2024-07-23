package com.myapp.web.rest;

import com.myapp.repository.FinancialDetailsRepository;
import com.myapp.service.FinancialDetailsService;
import com.myapp.service.dto.FinancialDetailsDTO;
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
 * REST controller for managing {@link com.myapp.domain.FinancialDetails}.
 */
@RestController
@RequestMapping("/api/financial-details")
public class FinancialDetailsResource {

    private static final Logger log = LoggerFactory.getLogger(FinancialDetailsResource.class);

    private static final String ENTITY_NAME = "financialDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinancialDetailsService financialDetailsService;

    private final FinancialDetailsRepository financialDetailsRepository;

    public FinancialDetailsResource(
        FinancialDetailsService financialDetailsService,
        FinancialDetailsRepository financialDetailsRepository
    ) {
        this.financialDetailsService = financialDetailsService;
        this.financialDetailsRepository = financialDetailsRepository;
    }

    /**
     * {@code POST  /financial-details} : Create a new financialDetails.
     *
     * @param financialDetailsDTO the financialDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new financialDetailsDTO, or with status {@code 400 (Bad Request)} if the financialDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FinancialDetailsDTO> createFinancialDetails(@Valid @RequestBody FinancialDetailsDTO financialDetailsDTO)
        throws URISyntaxException {
        log.debug("REST request to save FinancialDetails : {}", financialDetailsDTO);
        if (financialDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new financialDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        financialDetailsDTO = financialDetailsService.save(financialDetailsDTO);
        return ResponseEntity.created(new URI("/api/financial-details/" + financialDetailsDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, financialDetailsDTO.getId().toString()))
            .body(financialDetailsDTO);
    }

    /**
     * {@code PUT  /financial-details/:id} : Updates an existing financialDetails.
     *
     * @param id the id of the financialDetailsDTO to save.
     * @param financialDetailsDTO the financialDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financialDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the financialDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the financialDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FinancialDetailsDTO> updateFinancialDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FinancialDetailsDTO financialDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FinancialDetails : {}, {}", id, financialDetailsDTO);
        if (financialDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, financialDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!financialDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        financialDetailsDTO = financialDetailsService.update(financialDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, financialDetailsDTO.getId().toString()))
            .body(financialDetailsDTO);
    }

    /**
     * {@code PATCH  /financial-details/:id} : Partial updates given fields of an existing financialDetails, field will ignore if it is null
     *
     * @param id the id of the financialDetailsDTO to save.
     * @param financialDetailsDTO the financialDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financialDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the financialDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the financialDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the financialDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FinancialDetailsDTO> partialUpdateFinancialDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FinancialDetailsDTO financialDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FinancialDetails partially : {}, {}", id, financialDetailsDTO);
        if (financialDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, financialDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!financialDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FinancialDetailsDTO> result = financialDetailsService.partialUpdate(financialDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, financialDetailsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /financial-details} : get all the financialDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of financialDetails in body.
     */
    @GetMapping("")
    public List<FinancialDetailsDTO> getAllFinancialDetails() {
        log.debug("REST request to get all FinancialDetails");
        return financialDetailsService.findAll();
    }

    /**
     * {@code GET  /financial-details/:id} : get the "id" financialDetails.
     *
     * @param id the id of the financialDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the financialDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FinancialDetailsDTO> getFinancialDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get FinancialDetails : {}", id);
        Optional<FinancialDetailsDTO> financialDetailsDTO = financialDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(financialDetailsDTO);
    }

    /**
     * {@code DELETE  /financial-details/:id} : delete the "id" financialDetails.
     *
     * @param id the id of the financialDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinancialDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete FinancialDetails : {}", id);
        financialDetailsService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
