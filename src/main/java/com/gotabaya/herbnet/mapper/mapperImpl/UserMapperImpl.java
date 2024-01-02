package com.gotabaya.herbnet.mapper.mapperImpl;

import com.gotabaya.herbnet.mapper.UserMapper;
import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.SignUpRequest;
import com.gotabaya.herbnet.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
	private final PasswordEncoder passwordEncoder;
	@Override
	public UserDto toDto(User user) {
		return new UserDto(user.getUsername(), user.getEmail());
	}

	@Override
	public User toUser(SignUpRequest signUpRequest) {
		return new User(
				null,
				signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword())
		);
	}
}