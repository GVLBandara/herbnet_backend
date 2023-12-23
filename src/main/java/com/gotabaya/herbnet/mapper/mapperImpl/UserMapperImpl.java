package com.gotabaya.herbnet.mapper.mapperImpl;

import com.gotabaya.herbnet.mapper.UserMapper;
import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
	@Override
	public UserDto toDto(User user) {
		return new UserDto(user.getUsername(), user.getEmail());
	}
}