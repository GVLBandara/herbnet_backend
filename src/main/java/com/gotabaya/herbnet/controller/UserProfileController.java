package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.dto.UserProfileDto;
import com.gotabaya.herbnet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userprofile")
public class UserProfileController {
	final UserProfileService userProfileService;

	@GetMapping("")
	public List<UserProfileDto> findAll(){
		return userProfileService.findAll();
	}

	@GetMapping("/{profileId}")
	public UserProfileDto findById(@PathVariable("profileId") Long profileId){
		return userProfileService.findById(profileId);
	}
}
