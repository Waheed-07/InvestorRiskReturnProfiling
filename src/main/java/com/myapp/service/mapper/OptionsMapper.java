package com.myapp.service.mapper;

import com.myapp.domain.Options;
import com.myapp.service.dto.OptionsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Options} and its DTO {@link OptionsDTO}.
 */
@Mapper(componentModel = "spring")
public interface OptionsMapper extends EntityMapper<OptionsDTO, Options> {}
