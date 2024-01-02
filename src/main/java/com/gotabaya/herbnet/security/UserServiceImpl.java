package com.gotabaya.herbnet.security;

import com.gotabaya.herbnet.mapper.UserMapper;
import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.SignUpRequest;
import com.gotabaya.herbnet.model.dto.UserDto;
import com.gotabaya.herbnet.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User does not exist by Id " + userId));
        return userMapper.toDto(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public Optional<UserDto> validUsernameAndPassword(String username, String password) {
        return getUserByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword())).map(userMapper::toDto);
    }

    @Override
    public UserDto saveUser(SignUpRequest signUpRequest) {
        User user = userMapper.toUser(signUpRequest);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }
}
