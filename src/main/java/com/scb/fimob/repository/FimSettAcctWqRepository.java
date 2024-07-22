package com.scb.fimob.repository;

import com.scb.fimob.domain.FimSettAcctWq;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FimSettAcctWq entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FimSettAcctWqRepository extends JpaRepository<FimSettAcctWq, Long> {}
