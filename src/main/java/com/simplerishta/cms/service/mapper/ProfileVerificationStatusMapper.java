package com.simplerishta.cms.service.mapper;

import com.simplerishta.cms.domain.ProfileVerificationStatus;
import com.simplerishta.cms.service.dto.ProfileVerificationStatusDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProfileVerificationStatus} and its DTO {@link ProfileVerificationStatusDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProfileVerificationStatusMapper extends EntityMapper<ProfileVerificationStatusDTO, ProfileVerificationStatus> {}
