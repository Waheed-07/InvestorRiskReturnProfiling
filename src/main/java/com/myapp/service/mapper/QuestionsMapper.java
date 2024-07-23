package com.myapp.service.mapper;

import com.myapp.domain.Questions;
import com.myapp.service.dto.QuestionsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Questions} and its DTO {@link QuestionsDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionsMapper extends EntityMapper<QuestionsDTO, Questions> {}
