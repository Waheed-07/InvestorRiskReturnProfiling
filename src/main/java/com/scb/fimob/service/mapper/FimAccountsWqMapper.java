package com.scb.fimob.service.mapper;

import com.scb.fimob.domain.FimAccountsWq;
import com.scb.fimob.service.dto.FimAccountsWqDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FimAccountsWq} and its DTO {@link FimAccountsWqDTO}.
 */
@Mapper(componentModel = "spring")
public interface FimAccountsWqMapper extends EntityMapper<FimAccountsWqDTO, FimAccountsWq> {}
