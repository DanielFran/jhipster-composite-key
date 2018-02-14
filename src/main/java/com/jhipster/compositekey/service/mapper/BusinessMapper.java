package com.jhipster.compositekey.service.mapper;

import com.jhipster.compositekey.domain.*;
import com.jhipster.compositekey.service.dto.BusinessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Business and its DTO BusinessDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BusinessMapper extends EntityMapper<BusinessDTO, Business> {



    default Business fromId(Long id) {
        if (id == null) {
            return null;
        }
        Business business = new Business();
        business.setId(id);
        return business;
    }
}
