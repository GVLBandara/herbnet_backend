package com.gotabaya.herbnet.service;

import com.gotabaya.herbnet.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long userId);
}
