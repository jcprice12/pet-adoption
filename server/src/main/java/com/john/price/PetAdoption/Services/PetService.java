package com.john.price.PetAdoption.Services;

import java.util.Collection;

import com.john.price.PetAdoption.Models.Pet;

public interface PetService<T extends Pet> {
	public abstract Collection<T> getPets();
	public abstract T getPet(Integer id);
	public abstract T createPet(T t);
	public abstract T editPet(T t);
}