package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.validation.constraints.NotNull;

public abstract class PetWithBreeds extends Pet{
	@NotNull
	public abstract Set<Breed> getBreeds();
	public abstract void setBreeds(Set<Breed> breeds);
}
