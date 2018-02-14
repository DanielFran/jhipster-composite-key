package com.jhipster.compositekey.service.mapper;

import com.jhipster.compositekey.domain.*;
import com.jhipster.compositekey.service.dto.BasicIndexDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BasicIndex and its DTO BasicIndexDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasicIndexMapper extends EntityMapper<BasicIndexDTO, BasicIndex> {



    default BasicIndex fromId(Long id) {
        if (id == null) {
            return null;
        }
        BasicIndex basicIndex = new BasicIndex();
        basicIndex.setId(id);
        return basicIndex;
    }
}
