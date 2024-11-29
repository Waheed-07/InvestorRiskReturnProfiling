package com.scb.fimob.web.rest;

import com.scb.fimob.repository.EthnicityRepository;
import com.scb.fimob.service.EthnicityService;
import com.scb.fimob.service.dto.EthnicityDTO;
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
 * REST controller for managing {@link com.scb.fimob.domain.Ethnicity}.
 */
@RestController
@RequestMapping("/api/ethnicities")
public class EthnicityResource {

    private static final Logger log = LoggerFactory.getLogger(EthnicityResource.class);

    private static final String ENTITY_NAME = "fimEthnicity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EthnicityService ethnicityService;

    private final EthnicityRepository ethnicityRepository;

    public EthnicityResource(EthnicityService ethnicityService, EthnicityRepository ethnicityRepository) {
        this.ethnicityService = ethnicityService;
        this.ethnicityRepository = ethnicityRepository;
    }

    /**
     * {@code POST  /ethnicities} : Create a new ethnicity.
     *
     * @param ethnicityDTO the ethnicityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ethnicityDTO, or with status {@code 400 (Bad Request)} if the ethnicity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EthnicityDTO> createEthnicity(@Valid @RequestBody EthnicityDTO ethnicityDTO) throws URISyntaxException {
        log.debug("REST request to save Ethnicity : {}", ethnicityDTO);
        if (ethnicityDTO.getId() != null) {
            throw new BadRequestAlertException("A new ethnicity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ethnicityDTO = ethnicityService.save(ethnicityDTO);
        return ResponseEntity.created(new URI("/api/ethnicities/" + ethnicityDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, ethnicityDTO.getId().toString()))
            .body(ethnicityDTO);
    }

    /**
     * {@code PUT  /ethnicities/:id} : Updates an existing ethnicity.
     *
     * @param id the id of the ethnicityDTO to save.
     * @param ethnicityDTO the ethnicityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ethnicityDTO,
     * or with status {@code 400 (Bad Request)} if the ethnicityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ethnicityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EthnicityDTO> updateEthnicity(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody EthnicityDTO ethnicityDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Ethnicity : {}, {}", id, ethnicityDTO);
        if (ethnicityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ethnicityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ethnicityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ethnicityDTO = ethnicityService.update(ethnicityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ethnicityDTO.getId().toString()))
            .body(ethnicityDTO);
    }

    /**
     * {@code PATCH  /ethnicities/:id} : Partial updates given fields of an existing ethnicity, field will ignore if it is null
     *
     * @param id the id of the ethnicityDTO to save.
     * @param ethnicityDTO the ethnicityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ethnicityDTO,
     * or with status {@code 400 (Bad Request)} if the ethnicityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ethnicityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ethnicityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EthnicityDTO> partialUpdateEthnicity(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody EthnicityDTO ethnicityDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Ethnicity partially : {}, {}", id, ethnicityDTO);
        if (ethnicityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ethnicityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ethnicityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EthnicityDTO> result = ethnicityService.partialUpdate(ethnicityDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ethnicityDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ethnicities} : get all the ethnicities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ethnicities in body.
     */
    @GetMapping("")
    public List<EthnicityDTO> getAllEthnicities() {
        log.debug("REST request to get all Ethnicities");
        return ethnicityService.findAll();
    }

    /**
     * {@code GET  /ethnicities/:id} : get the "id" ethnicity.
     *
     * @param id the id of the ethnicityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ethnicityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EthnicityDTO> getEthnicity(@PathVariable("id") Long id) {
        log.debug("REST request to get Ethnicity : {}", id);
        Optional<EthnicityDTO> ethnicityDTO = ethnicityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ethnicityDTO);
    }

    /**
     * {@code DELETE  /ethnicities/:id} : delete the "id" ethnicity.
     *
     * @param id the id of the ethnicityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEthnicity(@PathVariable("id") Long id) {
        log.debug("REST request to delete Ethnicity : {}", id);
        ethnicityService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
