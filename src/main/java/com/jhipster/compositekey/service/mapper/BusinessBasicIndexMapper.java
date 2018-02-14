package com.jhipster.compositekey.service.mapper;

import com.jhipster.compositekey.domain.*;
import com.jhipster.compositekey.service.dto.BusinessBasicIndexDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BusinessBasicIndex and its DTO BusinessBasicIndexDTO.
 */
@Mapper(componentModel = "spring", uses = {BusinessMapper.class, BasicIndexMapper.class})
public interface BusinessBasicIndexMapper extends EntityMapper<BusinessBasicIndexDTO, BusinessBasicIndex> {

    @Mapping(source = "business.id", target = "businessId")
    @Mapping(source = "business.name", target = "businessName")
    @Mapping(source = "basicIndex.id", target = "basicIndexId")
    @Mapping(source = "basicIndex.name", target = "basicIndexName")
    BusinessBasicIndexDTO toDto(BusinessBasicIndex businessBasicIndex);

    @Mapping(source = "businessId", target = "business")
    @Mapping(source = "basicIndexId", target = "basicIndex")
    BusinessBasicIndex toEntity(BusinessBasicIndexDTO businessBasicIndexDTO);

    default BusinessBasicIndex fromId(Long id) {
        if (id == null) {
            return null;
        }
        BusinessBasicIndex businessBasicIndex = new BusinessBasicIndex();
        businessBasicIndex.setId(id);
        return businessBasicIndex;
    }
}
