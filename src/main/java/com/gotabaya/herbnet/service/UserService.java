package com.gotabaya.herbnet.service;

import com.gotabaya.herbnet.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public User findById(Long userId);
}
