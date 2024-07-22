package com.scb.fimob.repository;

import com.scb.fimob.domain.Ethnicity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Ethnicity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EthnicityRepository extends JpaRepository<Ethnicity, Long> {}
