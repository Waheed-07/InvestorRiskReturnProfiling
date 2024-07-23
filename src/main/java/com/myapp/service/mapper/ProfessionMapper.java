package com.myapp.service.mapper;

import com.myapp.domain.Profession;
import com.myapp.service.dto.ProfessionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profession} and its DTO {@link ProfessionDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProfessionMapper extends EntityMapper<ProfessionDTO, Profession> {}
