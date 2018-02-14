package com.jhipster.compositekey.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jhipster.compositekey.service.BusinessBasicIndexService;
import com.jhipster.compositekey.web.rest.errors.BadRequestAlertException;
import com.jhipster.compositekey.web.rest.util.HeaderUtil;
import com.jhipster.compositekey.service.dto.BusinessBasicIndexDTO;
import com.jhipster.compositekey.service.dto.BusinessBasicIndexCriteria;
import com.jhipster.compositekey.service.BusinessBasicIndexQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BusinessBasicIndex.
 */
@RestController
@RequestMapping("/api")
public class BusinessBasicIndexResource {

    private final Logger log = LoggerFactory.getLogger(BusinessBasicIndexResource.class);

    private static final String ENTITY_NAME = "businessBasicIndex";

    private final BusinessBasicIndexService businessBasicIndexService;

    private final BusinessBasicIndexQueryService businessBasicIndexQueryService;

    public BusinessBasicIndexResource(BusinessBasicIndexService businessBasicIndexService, BusinessBasicIndexQueryService businessBasicIndexQueryService) {
        this.businessBasicIndexService = businessBasicIndexService;
        this.businessBasicIndexQueryService = businessBasicIndexQueryService;
    }

    /**
     * POST  /business-basic-indices : Create a new businessBasicIndex.
     *
     * @param businessBasicIndexDTO the businessBasicIndexDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessBasicIndexDTO, or with status 400 (Bad Request) if the businessBasicIndex has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/business-basic-indices")
    @Timed
    public ResponseEntity<BusinessBasicIndexDTO> createBusinessBasicIndex(@Valid @RequestBody BusinessBasicIndexDTO businessBasicIndexDTO) throws URISyntaxException {
        log.debug("REST request to save BusinessBasicIndex : {}", businessBasicIndexDTO);
        if (businessBasicIndexDTO.getId() != null) {
            throw new BadRequestAlertException("A new businessBasicIndex cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessBasicIndexDTO result = businessBasicIndexService.save(businessBasicIndexDTO);
        return ResponseEntity.created(new URI("/api/business-basic-indices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /business-basic-indices : Updates an existing businessBasicIndex.
     *
     * @param businessBasicIndexDTO the businessBasicIndexDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessBasicIndexDTO,
     * or with status 400 (Bad Request) if the businessBasicIndexDTO is not valid,
     * or with status 500 (Internal Server Error) if the businessBasicIndexDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/business-basic-indices")
    @Timed
    public ResponseEntity<BusinessBasicIndexDTO> updateBusinessBasicIndex(@Valid @RequestBody BusinessBasicIndexDTO businessBasicIndexDTO) throws URISyntaxException {
        log.debug("REST request to update BusinessBasicIndex : {}", businessBasicIndexDTO);
        if (businessBasicIndexDTO.getId() == null) {
            return createBusinessBasicIndex(businessBasicIndexDTO);
        }
        BusinessBasicIndexDTO result = businessBasicIndexService.save(businessBasicIndexDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessBasicIndexDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /business-basic-indices : get all the businessBasicIndices.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of businessBasicIndices in body
     */
    @GetMapping("/business-basic-indices")
    @Timed
    public ResponseEntity<List<BusinessBasicIndexDTO>> getAllBusinessBasicIndices(BusinessBasicIndexCriteria criteria) {
        log.debug("REST request to get BusinessBasicIndices by criteria: {}", criteria);
        List<BusinessBasicIndexDTO> entityList = businessBasicIndexQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /business-basic-indices/:id : get the "id" businessBasicIndex.
     *
     * @param id the id of the businessBasicIndexDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessBasicIndexDTO, or with status 404 (Not Found)
     */
    @GetMapping("/business-basic-indices/{id}")
    @Timed
    public ResponseEntity<BusinessBasicIndexDTO> getBusinessBasicIndex(@PathVariable Long id) {
        log.debug("REST request to get BusinessBasicIndex : {}", id);
        BusinessBasicIndexDTO businessBasicIndexDTO = businessBasicIndexService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(businessBasicIndexDTO));
    }

    /**
     * DELETE  /business-basic-indices/:id : delete the "id" businessBasicIndex.
     *
     * @param id the id of the businessBasicIndexDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/business-basic-indices/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusinessBasicIndex(@PathVariable Long id) {
        log.debug("REST request to delete BusinessBasicIndex : {}", id);
        businessBasicIndexService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
