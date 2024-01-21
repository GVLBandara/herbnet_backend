package com.gotabaya.herbnet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Species {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "speciesid")
	private Long speciesId;
	@Column(name = "scientificname")
	private String scientificName;
	@Column(name = "plantname")
	private String plantName;
	private String description;
}
