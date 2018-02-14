package com.jhipster.compositekey.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jhipster.compositekey.service.BusinessService;
import com.jhipster.compositekey.web.rest.errors.BadRequestAlertException;
import com.jhipster.compositekey.web.rest.util.HeaderUtil;
import com.jhipster.compositekey.service.dto.BusinessDTO;
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
 * REST controller for managing Business.
 */
@RestController
@RequestMapping("/api")
public class BusinessResource {

    private final Logger log = LoggerFactory.getLogger(BusinessResource.class);

    private static final String ENTITY_NAME = "business";

    private final BusinessService businessService;

    public BusinessResource(BusinessService businessService) {
        this.businessService = businessService;
    }

    /**
     * POST  /businesses : Create a new business.
     *
     * @param businessDTO the businessDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessDTO, or with status 400 (Bad Request) if the business has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/businesses")
    @Timed
    public ResponseEntity<BusinessDTO> createBusiness(@Valid @RequestBody BusinessDTO businessDTO) throws URISyntaxException {
        log.debug("REST request to save Business : {}", businessDTO);
        if (businessDTO.getId() != null) {
            throw new BadRequestAlertException("A new business cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessDTO result = businessService.save(businessDTO);
        return ResponseEntity.created(new URI("/api/businesses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /businesses : Updates an existing business.
     *
     * @param businessDTO the businessDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessDTO,
     * or with status 400 (Bad Request) if the businessDTO is not valid,
     * or with status 500 (Internal Server Error) if the businessDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/businesses")
    @Timed
    public ResponseEntity<BusinessDTO> updateBusiness(@Valid @RequestBody BusinessDTO businessDTO) throws URISyntaxException {
        log.debug("REST request to update Business : {}", businessDTO);
        if (businessDTO.getId() == null) {
            return createBusiness(businessDTO);
        }
        BusinessDTO result = businessService.save(businessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /businesses : get all the businesses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of businesses in body
     */
    @GetMapping("/businesses")
    @Timed
    public List<BusinessDTO> getAllBusinesses() {
        log.debug("REST request to get all Businesses");
        return businessService.findAll();
        }

    /**
     * GET  /businesses/:id : get the "id" business.
     *
     * @param id the id of the businessDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessDTO, or with status 404 (Not Found)
     */
    @GetMapping("/businesses/{id}")
    @Timed
    public ResponseEntity<BusinessDTO> getBusiness(@PathVariable Long id) {
        log.debug("REST request to get Business : {}", id);
        BusinessDTO businessDTO = businessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(businessDTO));
    }

    /**
     * DELETE  /businesses/:id : delete the "id" business.
     *
     * @param id the id of the businessDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/businesses/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long id) {
        log.debug("REST request to delete Business : {}", id);
        businessService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
