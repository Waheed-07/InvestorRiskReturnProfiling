package com.scb.fimob.service.mapper;

import com.scb.fimob.domain.FimCust;
import com.scb.fimob.service.dto.FimCustDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FimCust} and its DTO {@link FimCustDTO}.
 */
@Mapper(componentModel = "spring")
public interface FimCustMapper extends EntityMapper<FimCustDTO, FimCust> {}
