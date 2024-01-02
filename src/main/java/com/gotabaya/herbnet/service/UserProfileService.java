package com.gotabaya.herbnet.service;

import com.gotabaya.herbnet.model.dto.UserProfileDto;

import java.util.List;

public interface UserProfileService {
	List<UserProfileDto> findAll();
	UserProfileDto findById(Long profileId);

	UserProfileDto currentUP(String username);
}
