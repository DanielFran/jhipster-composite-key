package com.jhipster.compositekey.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.jhipster.compositekey.domain.BusinessBasicIndex;
import com.jhipster.compositekey.domain.*; // for static metamodels
import com.jhipster.compositekey.repository.BusinessBasicIndexRepository;
import com.jhipster.compositekey.service.dto.BusinessBasicIndexCriteria;

import com.jhipster.compositekey.service.dto.BusinessBasicIndexDTO;
import com.jhipster.compositekey.service.mapper.BusinessBasicIndexMapper;

/**
 * Service for executing complex queries for BusinessBasicIndex entities in the database.
 * The main input is a {@link BusinessBasicIndexCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BusinessBasicIndexDTO} or a {@link Page} of {@link BusinessBasicIndexDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BusinessBasicIndexQueryService extends QueryService<BusinessBasicIndex> {

    private final Logger log = LoggerFactory.getLogger(BusinessBasicIndexQueryService.class);


    private final BusinessBasicIndexRepository businessBasicIndexRepository;

    private final BusinessBasicIndexMapper businessBasicIndexMapper;

    public BusinessBasicIndexQueryService(BusinessBasicIndexRepository businessBasicIndexRepository, BusinessBasicIndexMapper businessBasicIndexMapper) {
        this.businessBasicIndexRepository = businessBasicIndexRepository;
        this.businessBasicIndexMapper = businessBasicIndexMapper;
    }

    /**
     * Return a {@link List} of {@link BusinessBasicIndexDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BusinessBasicIndexDTO> findByCriteria(BusinessBasicIndexCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<BusinessBasicIndex> specification = createSpecification(criteria);
        return businessBasicIndexMapper.toDto(businessBasicIndexRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BusinessBasicIndexDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BusinessBasicIndexDTO> findByCriteria(BusinessBasicIndexCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<BusinessBasicIndex> specification = createSpecification(criteria);
        final Page<BusinessBasicIndex> result = businessBasicIndexRepository.findAll(specification, page);
        return result.map(businessBasicIndexMapper::toDto);
    }

    /**
     * Function to convert BusinessBasicIndexCriteria to a {@link Specifications}
     */
    private Specifications<BusinessBasicIndex> createSpecification(BusinessBasicIndexCriteria criteria) {
        Specifications<BusinessBasicIndex> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BusinessBasicIndex_.id));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), BusinessBasicIndex_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), BusinessBasicIndex_.month));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValue(), BusinessBasicIndex_.value));
            }
            if (criteria.getBusinessId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBusinessId(), BusinessBasicIndex_.business, Business_.id));
            }
            if (criteria.getBasicIndexId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBasicIndexId(), BusinessBasicIndex_.basicIndex, BasicIndex_.id));
            }
        }
        return specification;
    }

}
