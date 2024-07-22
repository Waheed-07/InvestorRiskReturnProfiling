package com.scb.fimob.repository;

import com.scb.fimob.domain.FimCustHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FimCustHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FimCustHistoryRepository extends JpaRepository<FimCustHistory, Long> {}
