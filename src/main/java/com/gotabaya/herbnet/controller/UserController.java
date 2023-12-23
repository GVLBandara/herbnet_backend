package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.dto.UserDto;
import com.gotabaya.herbnet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    public final UserService userService;

    @GetMapping("")
    public List<UserDto> getAll(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserDto findById(@PathVariable Long userId){
        return userService.findById(userId);
    }
}
