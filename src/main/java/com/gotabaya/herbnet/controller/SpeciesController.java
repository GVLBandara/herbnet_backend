package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.Species;
import com.gotabaya.herbnet.service.SpeciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/species")
@RequiredArgsConstructor
public class SpeciesController {
	final SpeciesService speciesService;

	@GetMapping("")
	public List<Species> findAll(){
		return speciesService.findAll();
	}
}
