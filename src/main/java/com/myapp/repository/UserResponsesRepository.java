package com.myapp.repository;

import com.myapp.domain.UserResponses;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the UserResponses entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserResponsesRepository extends JpaRepository<UserResponses, Long> {}
