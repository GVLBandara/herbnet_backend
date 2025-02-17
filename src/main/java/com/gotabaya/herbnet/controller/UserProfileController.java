package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.dto.UserProfileDto;
import com.gotabaya.herbnet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.security.Principal;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/userprofile")
public class UserProfileController {
	final UserProfileService userProfileService;

	@GetMapping("")
	public UserProfileDto currentUP(Principal principal){
		return userProfileService.currentUP(principal.getName());
	}

	@GetMapping("/{profileId}")
	public UserProfileDto findById(@PathVariable("profileId") Long profileId){
		return userProfileService.findById(profileId);
	}

	@PostMapping("")
	public ResponseEntity<String> newUP(@RequestBody UserProfileDto userProfileDto, Principal principal){
		try {
			userProfileService.newUP(userProfileDto, principal.getName());
		}catch (HttpServerErrorException e){
			return ResponseEntity.status(e.getStatusCode()).build();
		}
		return ResponseEntity.ok().build();
	}

	@PutMapping("")
	public ResponseEntity<String> updateUP(@RequestBody UserProfileDto userProfileDto, Principal principal){
		try {
			userProfileService.updateUP(userProfileDto, principal.getName());
		}catch (HttpServerErrorException e){
			return ResponseEntity.status(e.getStatusCode()).build();
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/available")
	public boolean profileAvailable(Principal principal){
		return userProfileService.isAvailable(principal.getName());
	}
}
