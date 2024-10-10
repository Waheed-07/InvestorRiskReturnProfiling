package com.simplerishta.cms.repository;

import com.simplerishta.cms.domain.ProfileVerificationStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProfileVerificationStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfileVerificationStatusRepository extends JpaRepository<ProfileVerificationStatus, Long> {}
