package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Breed<T extends PetWithBreeds<?>> {
	
	@NotNull
	private Integer id;

	@NotNull
	private String name;
	
	private Set<T> petsWithBreeds;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "breed_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	public Set<T> getPetsWithBreeds() {
		return this.petsWithBreeds;
	}

	public void setPetsWithBreeds(Set<T> petsWithBreeds) {
		this.petsWithBreeds = petsWithBreeds;
	}
}
