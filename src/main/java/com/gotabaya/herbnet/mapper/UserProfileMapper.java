package com.gotabaya.herbnet.mapper;

import com.gotabaya.herbnet.model.UserProfile;
import com.gotabaya.herbnet.model.dto.UserProfileDto;

public interface UserProfileMapper {
	UserProfileDto toDto(UserProfile userProfile);
	UserProfile toEntity(UserProfileDto userProfileDto);
}
