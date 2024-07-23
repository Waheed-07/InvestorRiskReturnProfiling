package com.myapp.service.mapper;

import com.myapp.domain.UserResponses;
import com.myapp.service.dto.UserResponsesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserResponses} and its DTO {@link UserResponsesDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserResponsesMapper extends EntityMapper<UserResponsesDTO, UserResponses> {}
