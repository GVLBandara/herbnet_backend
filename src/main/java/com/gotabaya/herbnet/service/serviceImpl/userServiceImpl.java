package com.gotabaya.herbnet.service.serviceImpl;

import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.repository.UserRepository;
import com.gotabaya.herbnet.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class userServiceImpl implements UserService {
    public final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User does not exist by Id "+userId));
    }
}
