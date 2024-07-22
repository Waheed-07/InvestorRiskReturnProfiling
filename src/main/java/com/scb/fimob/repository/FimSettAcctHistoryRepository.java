package com.scb.fimob.repository;

import com.scb.fimob.domain.FimSettAcctHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FimSettAcctHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FimSettAcctHistoryRepository extends JpaRepository<FimSettAcctHistory, Long> {}
