package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.Species;
import com.gotabaya.herbnet.service.SpeciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/species")
@RequiredArgsConstructor
public class SpeciesController {
	final SpeciesService speciesService;

	@GetMapping("")
	public List<String> findAllNames(){
		return speciesService.getAllNames();
	}

	@GetMapping("/id/{id}")
	public Species getSpecies(@PathVariable("id") long id){
		return speciesService.getSpecies(id);
	}

	@GetMapping("/name/{englishName}")
	public Species getSpecies(@PathVariable("englishName") String englishName){
		return speciesService.getSpecies(englishName);
	}
}
