package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.repository.UserRepository;
import com.gotabaya.herbnet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {
    public final UserService userService;

    @GetMapping("")
    public List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("{userId}")
    public User findById(@PathVariable Long userId){
        return userService.findById(userId);
    }
}
