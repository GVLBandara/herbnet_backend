package com.gotabaya.herbnet.service.serviceImpl;

import com.gotabaya.herbnet.model.UserProfile;
import com.gotabaya.herbnet.repository.UserProfileRepository;
import com.gotabaya.herbnet.service.UserProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
	final UserProfileRepository userProfileRepository;
	@Override
	public List<UserProfile> findAll() {
		return userProfileRepository.findAll();
	}

	@Override
	public UserProfile findById(Long profileId) {
		return userProfileRepository.findById(profileId).orElseThrow(() -> new EntityNotFoundException("User profile doesn't exist by id "+ profileId));
	}
}
