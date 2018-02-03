package com.john.price.PetAdoption.Responses.Structs;

public class DogBreedStruct {
	public String name;
	public Integer id;
	
	public DogBreedStruct() {}
	
	public DogBreedStruct(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
