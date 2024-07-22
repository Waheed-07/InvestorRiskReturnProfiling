package com.scb.fimob.service.mapper;

import com.scb.fimob.domain.FimCustHistory;
import com.scb.fimob.service.dto.FimCustHistoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FimCustHistory} and its DTO {@link FimCustHistoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface FimCustHistoryMapper extends EntityMapper<FimCustHistoryDTO, FimCustHistory> {}
