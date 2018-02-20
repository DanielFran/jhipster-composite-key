package com.jhipster.compositekey.service.impl;

import com.jhipster.compositekey.domain.BusinessBasicIndex;
import com.jhipster.compositekey.domain.BusinessBasicIndexId;
import com.jhipster.compositekey.repository.BusinessBasicIndexRepository;
import com.jhipster.compositekey.service.BusinessBasicIndexService;
import com.jhipster.compositekey.service.dto.BusinessBasicIndexDTO;
import com.jhipster.compositekey.service.mapper.BusinessBasicIndexMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing BusinessBasicIndex.
 */
@Service
@Transactional
public class BusinessBasicIndexServiceImpl implements BusinessBasicIndexService {

    private final Logger log = LoggerFactory.getLogger(BusinessBasicIndexServiceImpl.class);

    private final BusinessBasicIndexRepository businessBasicIndexRepository;

    private final BusinessBasicIndexMapper businessBasicIndexMapper;

    public BusinessBasicIndexServiceImpl(BusinessBasicIndexRepository businessBasicIndexRepository, BusinessBasicIndexMapper businessBasicIndexMapper) {
        this.businessBasicIndexRepository = businessBasicIndexRepository;
        this.businessBasicIndexMapper = businessBasicIndexMapper;
    }

    /**
     * Validate if one businessBasicIndex exists.
     *
     * @param id the id of the entity
     * @return boolean
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(BusinessBasicIndexId id) {
        log.debug("Request if exists BusinessBasicIndex : {}", id);
        return businessBasicIndexRepository.exists(id);
    }

    /**
     * Save a businessBasicIndex.
     *
     * @param businessBasicIndexDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BusinessBasicIndexDTO save(BusinessBasicIndexDTO businessBasicIndexDTO) {
        log.debug("Request to save BusinessBasicIndex : {}", businessBasicIndexDTO);
        BusinessBasicIndex businessBasicIndex = businessBasicIndexMapper.toEntity(businessBasicIndexDTO);
        businessBasicIndex = businessBasicIndexRepository.save(businessBasicIndex);
        return businessBasicIndexMapper.toDto(businessBasicIndex);
    }

    /**
     * Get all the businessBasicIndices.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BusinessBasicIndexDTO> findAll() {
        log.debug("Request to get all BusinessBasicIndices");
        return businessBasicIndexRepository.findAll().stream()
            .map(businessBasicIndexMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one businessBasicIndex by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BusinessBasicIndexDTO findOne(BusinessBasicIndexId id) {
        log.debug("Request to get BusinessBasicIndex : {}", id);
        BusinessBasicIndex businessBasicIndex = businessBasicIndexRepository.findOne(id);
        return businessBasicIndexMapper.toDto(businessBasicIndex);
    }

    /**
     * Delete the businessBasicIndex by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(BusinessBasicIndexId id) {
        log.debug("Request to delete BusinessBasicIndex : {}", id);
        businessBasicIndexRepository.delete(id);
    }
}
