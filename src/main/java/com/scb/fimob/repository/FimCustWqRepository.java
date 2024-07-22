package com.scb.fimob.repository;

import com.scb.fimob.domain.FimCustWq;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FimCustWq entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FimCustWqRepository extends JpaRepository<FimCustWq, Long> {}
