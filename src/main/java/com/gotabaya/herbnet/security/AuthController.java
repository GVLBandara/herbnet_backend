package com.gotabaya.herbnet.security;

import com.gotabaya.herbnet.security.exception.DuplicatedUserInfoException;
import com.gotabaya.herbnet.model.dto.AuthResponse;
import com.gotabaya.herbnet.model.dto.LoginRequest;
import com.gotabaya.herbnet.model.dto.SignUpRequest;
import com.gotabaya.herbnet.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("")
    public List<UserDto> getAll(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserDto findById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<UserDto> userOptional = userService.validUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            return ResponseEntity.ok(new AuthResponse(user.username(), "USER"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
            throw new DuplicatedUserInfoException(String.format("Username %s is already been used", signUpRequest.getUsername()));
        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new DuplicatedUserInfoException(String.format("Email %s is already been used", signUpRequest.getEmail()));
        }

        UserDto user = userService.saveUser(signUpRequest);
        return new AuthResponse(user.username(), "USER");
    }
}
