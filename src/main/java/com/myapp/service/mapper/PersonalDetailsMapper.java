package com.myapp.service.mapper;

import com.myapp.domain.PersonalDetails;
import com.myapp.service.dto.PersonalDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PersonalDetails} and its DTO {@link PersonalDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface PersonalDetailsMapper extends EntityMapper<PersonalDetailsDTO, PersonalDetails> {}
