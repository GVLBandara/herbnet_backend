package com.gotabaya.herbnet.service.serviceImpl;

import com.gotabaya.herbnet.model.Species;
import com.gotabaya.herbnet.repository.SpeciesRepository;
import com.gotabaya.herbnet.service.SpeciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {
	final SpeciesRepository speciesRepository;
	@Override
	public List<String> getAllNames() {
		return speciesRepository.findAll().stream().map(Species::getCommonName).toList();
	}
}