package com.myapp.repository;

import com.myapp.domain.FinancialDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FinancialDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinancialDetailsRepository extends JpaRepository<FinancialDetails, Long> {}
