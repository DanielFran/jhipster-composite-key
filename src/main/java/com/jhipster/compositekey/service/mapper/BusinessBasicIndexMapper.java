package com.jhipster.compositekey.service.mapper;


import com.jhipster.compositekey.domain.BusinessBasicIndex;
import com.jhipster.compositekey.domain.BusinessBasicIndexId;
import com.jhipster.compositekey.service.dto.BusinessBasicIndexDTO;
import com.jhipster.compositekey.service.mapper.BasicIndexMapper;
import com.jhipster.compositekey.service.mapper.BusinessMapper;
import com.jhipster.compositekey.service.mapper.EntityMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity BusinessBasicIndex and its DTO BusinessBasicIndexDTO.
 */
@Mapper(componentModel = "spring", uses = {BusinessMapper.class, BasicIndexMapper.class})
public interface BusinessBasicIndexMapper extends EntityMapper<BusinessBasicIndexDTO, BusinessBasicIndex> {

    @Mapping(source = "id.businessId", target = "businessId")
    @Mapping(source = "business.name", target = "businessName")
    @Mapping(source = "id.basicIndexId", target = "basicIndexId")
    @Mapping(source = "basicIndex.name", target = "basicIndexName")
    @Mapping(source = "id.year", target = "year")
    BusinessBasicIndexDTO toDto(BusinessBasicIndex businessBasicIndex);

    @Mapping(source = "businessId", target = "business")
    @Mapping(source = "basicIndexId", target = "basicIndex")
    @Mapping(source = "businessId", target = "id.businessId")
    @Mapping(source = "basicIndexId", target = "id.basicIndexId")
    @Mapping(source = "year", target = "id.year")
    BusinessBasicIndex toEntity(BusinessBasicIndexDTO businessBasicIndexDTO);

    default BusinessBasicIndex fromId(BusinessBasicIndexId id) {
        if (id == null) {
            return null;
        }
        BusinessBasicIndex businessBasicIndex = new BusinessBasicIndex();
        businessBasicIndex.setId(id);
        return businessBasicIndex;
    }
}
