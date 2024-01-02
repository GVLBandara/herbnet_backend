package com.gotabaya.herbnet.security;

import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.SignUpRequest;
import com.gotabaya.herbnet.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<UserDto> findAll();
    UserDto findById(Long userId);
    Optional<User> getUserByUsername(String username);
    boolean hasUserWithUsername(String username);
    boolean hasUserWithEmail(String email);
    Optional<UserDto> validUsernameAndPassword(String username, String password);
    UserDto saveUser(SignUpRequest signUpRequest);
}
