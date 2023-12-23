package com.gotabaya.herbnet.repository;

import com.gotabaya.herbnet.model.Species;
import org.springframework.data.repository.ListCrudRepository;

public interface SpeciesRepository extends ListCrudRepository<Species, Long> {
}
