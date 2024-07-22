package com.scb.fimob.service.mapper;

import com.scb.fimob.domain.FimAccounts;
import com.scb.fimob.service.dto.FimAccountsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FimAccounts} and its DTO {@link FimAccountsDTO}.
 */
@Mapper(componentModel = "spring")
public interface FimAccountsMapper extends EntityMapper<FimAccountsDTO, FimAccounts> {}
