package com.gotabaya.herbnet.service.serviceImpl;

import com.gotabaya.herbnet.mapper.UserProfileMapper;
import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.UserProfile;
import com.gotabaya.herbnet.model.dto.UserProfileDto;
import com.gotabaya.herbnet.repository.UserProfileRepository;
import com.gotabaya.herbnet.repository.UserRepository;
import com.gotabaya.herbnet.security.exception.UserNotFoundException;
import com.gotabaya.herbnet.service.UserProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
	final UserRepository userRepository;
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

	@Override
	public UserProfileDto currentUP(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User doesn't exist by username " + username));
		UserProfile userProfile = userProfileRepository.findUserProfileByUser(user).orElseThrow(() -> new EntityNotFoundException("User profile doesn't exist by username " + username));
		return userProfileMapper.toDto(userProfile);
	}
}