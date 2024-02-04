package com.gotabaya.herbnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productid")
	private Long productId;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private User user;

	@ManyToOne
	@JoinColumn(name = "speciesid", referencedColumnName = "speciesid")
	private Species species;

	@Column(name = "plantorgan")
	private String plantOrgan;

	@Column(name = "processingmethod")
	private String processingMethod;

	@Column(name = "price")
	private Double price;

	private String location;

	private String description;

	@Column(name = "information")
	private String additionalInformation;

	@Column(name = "harvestdate")
	private LocalDateTime harvestDate;

	@Column(name = "listingdate")
	private LocalDateTime listingDate;
}