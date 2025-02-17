package com.gotabaya.herbnet.mapper.mapperImpl;

import com.gotabaya.herbnet.mapper.UserProfileMapper;
import com.gotabaya.herbnet.model.UserProfile;
import com.gotabaya.herbnet.model.dto.UserProfileDto;
import org.springframework.stereotype.Service;

@Service
public class UserProfileMapperImpl implements UserProfileMapper {
	@Override
	public UserProfileDto toDto(UserProfile userProfile) {
		return new UserProfileDto(
				userProfile.getFirstName(),
				userProfile.getLastName(),
				userProfile.getPhone(),
				userProfile.getUser().getEmail(),
				userProfile.getProfilePictureURL()
		);
	}

	@Override
	public UserProfile toEntity(UserProfileDto userProfileDto) {
		return new UserProfile(
				null,
				null,
				userProfileDto.firstName(),
				userProfileDto.lastName(),
				userProfileDto.phone(),
				null
		);
	}
}