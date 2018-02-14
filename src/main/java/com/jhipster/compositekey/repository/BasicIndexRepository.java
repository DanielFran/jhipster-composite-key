package com.jhipster.compositekey.repository;

import com.jhipster.compositekey.domain.BasicIndex;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BasicIndex entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BasicIndexRepository extends JpaRepository<BasicIndex, Long> {

}
