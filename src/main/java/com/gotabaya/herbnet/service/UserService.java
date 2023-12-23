package com.gotabaya.herbnet.service;

import com.gotabaya.herbnet.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();
    UserDto findById(Long userId);
}
