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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

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

	@Override
	public void newUP(UserProfileDto userProfileDto, String username) {
		User current_user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User doesn't exist by username " + username));
		if(userProfileRepository.findUserProfileByUser(current_user).isPresent()) throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
		else {
			UserProfile newUP = userProfileMapper.toEntity(userProfileDto);
			newUP.setUser(current_user);
			userProfileRepository.save(newUP);
		}
	}

	@Override
	public void updateUP(UserProfileDto userProfileDto, String username) {
		User current_user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User doesn't exist by username " + username));
		UserProfile userProfile = userProfileRepository.findUserProfileByUser(current_user).orElseThrow(() -> new EntityNotFoundException("User profile doesn't exist by username " + username));
		UserProfile updatedUP = userProfileMapper.toEntity(userProfileDto);

		updatedUP.setProfileId(userProfile.getProfileId());
		current_user.setEmail(userProfileDto.email());
		updatedUP.setUser(current_user);
		userProfileRepository.save(updatedUP);
		userRepository.save(current_user);
	}

	@Override
	public boolean isAvailable(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User doesn't exist by username " + username));
		return userProfileRepository.findUserProfileByUser(user).isPresent();
	}
}