package com.john.price.PetAdoption.Response;

public class BreedStruct {
	public String name;
	public Integer id;
	
	public BreedStruct() {}
	
	public BreedStruct(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
