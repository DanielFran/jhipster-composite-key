package com.jhipster.compositekey.service;

import com.jhipster.compositekey.domain.BusinessBasicIndexId;
import com.jhipster.compositekey.service.dto.BusinessBasicIndexDTO;

import java.util.List;


/**
 * Service Interface for managing BusinessBasicIndex.
 */
public interface BusinessBasicIndexService {

    /**
     * Save a businessBasicIndex.
     *
     * @param businessBasicIndexDTO the entity to save
     * @return the persisted entity
     */
    BusinessBasicIndexDTO save(BusinessBasicIndexDTO businessBasicIndexDTO);

    /**
     * Get all the businessBasicIndices.
     *
     * @return the list of entities
     */
    List<BusinessBasicIndexDTO> findAll();

    /**
     * Get the "id" businessBasicIndex.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BusinessBasicIndexDTO findOne(BusinessBasicIndexId id);

    /**
     * Delete the "id" businessBasicIndex.
     *
     * @param id the id of the entity
     */
    void delete(BusinessBasicIndexId id);
}
