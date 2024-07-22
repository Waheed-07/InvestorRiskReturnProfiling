package com.scb.fimob.service.mapper;

import com.scb.fimob.domain.FimSettAcctHistory;
import com.scb.fimob.service.dto.FimSettAcctHistoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FimSettAcctHistory} and its DTO {@link FimSettAcctHistoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface FimSettAcctHistoryMapper extends EntityMapper<FimSettAcctHistoryDTO, FimSettAcctHistory> {}
