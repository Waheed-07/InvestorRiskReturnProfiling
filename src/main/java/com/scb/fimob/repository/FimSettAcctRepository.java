package com.scb.fimob.repository;

import com.scb.fimob.domain.FimSettAcct;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FimSettAcct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FimSettAcctRepository extends JpaRepository<FimSettAcct, Long> {}
