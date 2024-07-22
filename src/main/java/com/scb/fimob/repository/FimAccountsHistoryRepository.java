package com.scb.fimob.repository;

import com.scb.fimob.domain.FimAccountsHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FimAccountsHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FimAccountsHistoryRepository extends JpaRepository<FimAccountsHistory, Long> {}
