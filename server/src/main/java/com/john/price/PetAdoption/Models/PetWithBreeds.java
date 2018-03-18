package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.validation.constraints.NotNull;

public abstract class PetWithBreeds extends Pet{
	protected Set<Breed> breeds;
	
	@NotNull
	public abstract Set<Breed> getBreeds();
	public abstract void setBreeds(Set<Breed> breeds);
	
	public PetWithBreeds() {}
	
	public PetWithBreeds(String name, String image, String description) {
		super(name, image, description);
	}
	
	public PetWithBreeds(Integer id, String name, String image, String description) {
		super(id, name, image, description);
	}
	
	public PetWithBreeds(String name, String image, String description, Set<Breed> breeds) {
		super(name, image, description);
		this.breeds = breeds;
	}
	
	public PetWithBreeds(Integer id, String name, String image, String description, Set<Breed> breeds) {
		super(id, name, image, description);
		this.breeds = breeds;
	}
}
