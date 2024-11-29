package com.scb.fimob.repository;

import com.scb.fimob.domain.FimCust;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FimCust entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FimCustRepository extends JpaRepository<FimCust, Long> {}
