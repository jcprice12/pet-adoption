package com.john.price.PetAdoption.Responses;

import com.john.price.PetAdoption.Models.Pet;

public abstract class PetResponse extends Pet{
	public PetResponse(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
