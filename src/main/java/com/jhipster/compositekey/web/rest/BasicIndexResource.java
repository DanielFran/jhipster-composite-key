package com.jhipster.compositekey.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jhipster.compositekey.service.BasicIndexService;
import com.jhipster.compositekey.web.rest.errors.BadRequestAlertException;
import com.jhipster.compositekey.web.rest.util.HeaderUtil;
import com.jhipster.compositekey.service.dto.BasicIndexDTO;
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
 * REST controller for managing BasicIndex.
 */
@RestController
@RequestMapping("/api")
public class BasicIndexResource {

    private final Logger log = LoggerFactory.getLogger(BasicIndexResource.class);

    private static final String ENTITY_NAME = "basicIndex";

    private final BasicIndexService basicIndexService;

    public BasicIndexResource(BasicIndexService basicIndexService) {
        this.basicIndexService = basicIndexService;
    }

    /**
     * POST  /basic-indices : Create a new basicIndex.
     *
     * @param basicIndexDTO the basicIndexDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new basicIndexDTO, or with status 400 (Bad Request) if the basicIndex has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/basic-indices")
    @Timed
    public ResponseEntity<BasicIndexDTO> createBasicIndex(@Valid @RequestBody BasicIndexDTO basicIndexDTO) throws URISyntaxException {
        log.debug("REST request to save BasicIndex : {}", basicIndexDTO);
        if (basicIndexDTO.getId() != null) {
            throw new BadRequestAlertException("A new basicIndex cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BasicIndexDTO result = basicIndexService.save(basicIndexDTO);
        return ResponseEntity.created(new URI("/api/basic-indices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /basic-indices : Updates an existing basicIndex.
     *
     * @param basicIndexDTO the basicIndexDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated basicIndexDTO,
     * or with status 400 (Bad Request) if the basicIndexDTO is not valid,
     * or with status 500 (Internal Server Error) if the basicIndexDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/basic-indices")
    @Timed
    public ResponseEntity<BasicIndexDTO> updateBasicIndex(@Valid @RequestBody BasicIndexDTO basicIndexDTO) throws URISyntaxException {
        log.debug("REST request to update BasicIndex : {}", basicIndexDTO);
        if (basicIndexDTO.getId() == null) {
            return createBasicIndex(basicIndexDTO);
        }
        BasicIndexDTO result = basicIndexService.save(basicIndexDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, basicIndexDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /basic-indices : get all the basicIndices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of basicIndices in body
     */
    @GetMapping("/basic-indices")
    @Timed
    public List<BasicIndexDTO> getAllBasicIndices() {
        log.debug("REST request to get all BasicIndices");
        return basicIndexService.findAll();
        }

    /**
     * GET  /basic-indices/:id : get the "id" basicIndex.
     *
     * @param id the id of the basicIndexDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the basicIndexDTO, or with status 404 (Not Found)
     */
    @GetMapping("/basic-indices/{id}")
    @Timed
    public ResponseEntity<BasicIndexDTO> getBasicIndex(@PathVariable Long id) {
        log.debug("REST request to get BasicIndex : {}", id);
        BasicIndexDTO basicIndexDTO = basicIndexService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(basicIndexDTO));
    }

    /**
     * DELETE  /basic-indices/:id : delete the "id" basicIndex.
     *
     * @param id the id of the basicIndexDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/basic-indices/{id}")
    @Timed
    public ResponseEntity<Void> deleteBasicIndex(@PathVariable Long id) {
        log.debug("REST request to delete BasicIndex : {}", id);
        basicIndexService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
