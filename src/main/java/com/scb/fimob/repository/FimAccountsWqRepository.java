package com.scb.fimob.repository;

import com.scb.fimob.domain.FimAccountsWq;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FimAccountsWq entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FimAccountsWqRepository extends JpaRepository<FimAccountsWq, Long> {}
