package com.gotabaya.herbnet.mapper;

import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.UserDto;

public interface UserMapper {
	UserDto toDto(User user);
}
