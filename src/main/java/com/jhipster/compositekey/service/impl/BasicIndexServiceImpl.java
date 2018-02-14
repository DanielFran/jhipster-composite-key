package com.jhipster.compositekey.service.impl;

import com.jhipster.compositekey.service.BasicIndexService;
import com.jhipster.compositekey.domain.BasicIndex;
import com.jhipster.compositekey.repository.BasicIndexRepository;
import com.jhipster.compositekey.service.dto.BasicIndexDTO;
import com.jhipster.compositekey.service.mapper.BasicIndexMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing BasicIndex.
 */
@Service
@Transactional
public class BasicIndexServiceImpl implements BasicIndexService {

    private final Logger log = LoggerFactory.getLogger(BasicIndexServiceImpl.class);

    private final BasicIndexRepository basicIndexRepository;

    private final BasicIndexMapper basicIndexMapper;

    public BasicIndexServiceImpl(BasicIndexRepository basicIndexRepository, BasicIndexMapper basicIndexMapper) {
        this.basicIndexRepository = basicIndexRepository;
        this.basicIndexMapper = basicIndexMapper;
    }

    /**
     * Save a basicIndex.
     *
     * @param basicIndexDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BasicIndexDTO save(BasicIndexDTO basicIndexDTO) {
        log.debug("Request to save BasicIndex : {}", basicIndexDTO);
        BasicIndex basicIndex = basicIndexMapper.toEntity(basicIndexDTO);
        basicIndex = basicIndexRepository.save(basicIndex);
        return basicIndexMapper.toDto(basicIndex);
    }

    /**
     * Get all the basicIndices.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BasicIndexDTO> findAll() {
        log.debug("Request to get all BasicIndices");
        return basicIndexRepository.findAll().stream()
            .map(basicIndexMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one basicIndex by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BasicIndexDTO findOne(Long id) {
        log.debug("Request to get BasicIndex : {}", id);
        BasicIndex basicIndex = basicIndexRepository.findOne(id);
        return basicIndexMapper.toDto(basicIndex);
    }

    /**
     * Delete the basicIndex by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BasicIndex : {}", id);
        basicIndexRepository.delete(id);
    }
}
