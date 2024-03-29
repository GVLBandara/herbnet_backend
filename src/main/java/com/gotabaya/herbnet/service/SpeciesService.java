package com.gotabaya.herbnet.service;

import com.gotabaya.herbnet.model.Species;

import java.util.List;

public interface SpeciesService {
	List<String> getAllNames();
	Species getSpecies(String englishName);
	Species getSpecies(long id);
}
