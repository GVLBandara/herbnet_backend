package com.gotabaya.herbnet.service;

import com.gotabaya.herbnet.model.UserProfile;

import java.util.List;

public interface UserProfileService {
	List<UserProfile> findAll();
	UserProfile findById(Long profileId);
}
