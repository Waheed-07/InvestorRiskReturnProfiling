package com.scb.fimob.service.mapper;

import com.scb.fimob.domain.FimAccountsHistory;
import com.scb.fimob.service.dto.FimAccountsHistoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FimAccountsHistory} and its DTO {@link FimAccountsHistoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface FimAccountsHistoryMapper extends EntityMapper<FimAccountsHistoryDTO, FimAccountsHistory> {}
