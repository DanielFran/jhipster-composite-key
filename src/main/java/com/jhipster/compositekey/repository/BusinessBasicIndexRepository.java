package com.jhipster.compositekey.repository;

import com.jhipster.compositekey.domain.BusinessBasicIndex;
import com.jhipster.compositekey.domain.BusinessBasicIndexId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the BusinessBasicIndex entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessBasicIndexRepository extends JpaRepository<BusinessBasicIndex, BusinessBasicIndexId>, JpaSpecificationExecutor<BusinessBasicIndex> {

}
