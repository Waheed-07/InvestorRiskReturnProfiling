package com.scb.fimob.service.mapper;

import com.scb.fimob.domain.FimSettAcct;
import com.scb.fimob.service.dto.FimSettAcctDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FimSettAcct} and its DTO {@link FimSettAcctDTO}.
 */
@Mapper(componentModel = "spring")
public interface FimSettAcctMapper extends EntityMapper<FimSettAcctDTO, FimSettAcct> {}
