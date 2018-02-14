package com.jhipster.compositekey.service;

import com.jhipster.compositekey.service.dto.BasicIndexDTO;
import java.util.List;

/**
 * Service Interface for managing BasicIndex.
 */
public interface BasicIndexService {

    /**
     * Save a basicIndex.
     *
     * @param basicIndexDTO the entity to save
     * @return the persisted entity
     */
    BasicIndexDTO save(BasicIndexDTO basicIndexDTO);

    /**
     * Get all the basicIndices.
     *
     * @return the list of entities
     */
    List<BasicIndexDTO> findAll();

    /**
     * Get the "id" basicIndex.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BasicIndexDTO findOne(Long id);

    /**
     * Delete the "id" basicIndex.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
