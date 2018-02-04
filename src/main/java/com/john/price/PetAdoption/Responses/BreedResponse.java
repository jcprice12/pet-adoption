package com.john.price.PetAdoption.Responses;

import com.john.price.PetAdoption.Models.Breed;

public class BreedResponse extends Breed {
	public BreedResponse(Integer id, String name){
		this.id = id;
		this.name = name;
	}
}
