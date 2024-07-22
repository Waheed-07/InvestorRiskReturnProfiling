package com.scb.fimob.service.mapper;

import com.scb.fimob.domain.FimSettAcctWq;
import com.scb.fimob.service.dto.FimSettAcctWqDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FimSettAcctWq} and its DTO {@link FimSettAcctWqDTO}.
 */
@Mapper(componentModel = "spring")
public interface FimSettAcctWqMapper extends EntityMapper<FimSettAcctWqDTO, FimSettAcctWq> {}
