package com.myapp.service.mapper;

import com.myapp.domain.FinancialDetails;
import com.myapp.service.dto.FinancialDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FinancialDetails} and its DTO {@link FinancialDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface FinancialDetailsMapper extends EntityMapper<FinancialDetailsDTO, FinancialDetails> {}
