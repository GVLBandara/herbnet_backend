package com.gotabaya.herbnet.repository;

import com.gotabaya.herbnet.model.Species;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface SpeciesRepository extends ListCrudRepository<Species, Long> {
	Optional<Species> findSpeciesByEnglishName(String name);
}
