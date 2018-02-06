package com.john.price.PetAdoption.Models;

import java.util.Set;

public abstract class PetWithBreeds extends Pet{
	public abstract Set<Breed> getBreeds();
	public abstract void setBreeds(Set<Breed> breeds);
}
