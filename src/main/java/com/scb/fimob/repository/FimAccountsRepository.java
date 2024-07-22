package com.scb.fimob.repository;

import com.scb.fimob.domain.FimAccounts;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FimAccounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FimAccountsRepository extends JpaRepository<FimAccounts, Long> {}
