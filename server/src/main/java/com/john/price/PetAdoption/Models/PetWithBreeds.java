package com.john.price.PetAdoption.Models;

import java.util.Set;

public abstract class PetWithBreeds extends Pet{
	protected Set<Breed> breeds;
	
	public abstract Set<Breed> getBreeds();
	public abstract void setBreeds(Set<Breed> breeds);
	
	public PetWithBreeds() {}
	
	public PetWithBreeds(Integer id, String name, String image, String description) {
		super(id, name, image, description);
	}
}
