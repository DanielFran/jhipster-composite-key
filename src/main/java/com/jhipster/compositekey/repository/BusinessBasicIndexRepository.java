package com.jhipster.compositekey.repository;

import com.jhipster.compositekey.domain.BusinessBasicIndex;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BusinessBasicIndex entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessBasicIndexRepository extends JpaRepository<BusinessBasicIndex, Long>, JpaSpecificationExecutor<BusinessBasicIndex> {

}
