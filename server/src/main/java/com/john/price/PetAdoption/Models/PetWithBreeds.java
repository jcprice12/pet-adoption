package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public abstract class PetWithBreeds extends Pet{
	
	@NotNull
	@Valid
	protected Set<Breed> breeds;
	
	public abstract Set<Breed> getBreeds();
	public abstract void setBreeds(Set<Breed> breeds);
	
	public PetWithBreeds() {}
	
	public PetWithBreeds(Integer id, String name, String image, String description) {
		super(id, name, image, description);
	}
}
