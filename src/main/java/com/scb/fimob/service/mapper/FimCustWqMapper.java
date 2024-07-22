package com.scb.fimob.service.mapper;

import com.scb.fimob.domain.FimCustWq;
import com.scb.fimob.service.dto.FimCustWqDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FimCustWq} and its DTO {@link FimCustWqDTO}.
 */
@Mapper(componentModel = "spring")
public interface FimCustWqMapper extends EntityMapper<FimCustWqDTO, FimCustWq> {}
