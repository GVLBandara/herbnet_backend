package com.gotabaya.herbnet.service.serviceImpl;

import com.gotabaya.herbnet.mapper.UserProfileMapper;
import com.gotabaya.herbnet.model.UserProfile;
import com.gotabaya.herbnet.model.dto.UserProfileDto;
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
	final UserProfileMapper userProfileMapper;
	@Override
	public List<UserProfileDto> findAll() {
		return userProfileRepository.findAll().stream().map(userProfileMapper::toDto).toList();
	}

	@Override
	public UserProfileDto findById(Long profileId) {
		UserProfile userProfile = userProfileRepository.findById(profileId).orElseThrow(() -> new EntityNotFoundException("User profile doesn't exist by id " + profileId));
		return userProfileMapper.toDto(userProfile);
	}
}
