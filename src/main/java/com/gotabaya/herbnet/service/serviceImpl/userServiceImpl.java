package com.gotabaya.herbnet.service.serviceImpl;

import com.gotabaya.herbnet.mapper.UserMapper;
import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.UserDto;
import com.gotabaya.herbnet.repository.UserRepository;
import com.gotabaya.herbnet.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class userServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User does not exist by Id " + userId));
        return userMapper.toDto(user);
    }
}
