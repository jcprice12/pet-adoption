package com.john.price.PetAdoption.Response;

import java.util.ArrayList;

import com.john.price.PetAdoption.Models.Breed;

public class PetResponse {
	private String name;
	private Integer id;
	private ArrayList<BreedStruct> breeds;
	
	public PetResponse() {}
	
	public PetResponse(Integer id, String name, Iterable<Breed> breeds) {
		this.setId(id);
		this.setName(name);
		this.breeds = new ArrayList<BreedStruct>();
		for(Breed breed : breeds) {
			this.breeds.add(new BreedStruct(breed.getBreedId(), breed.getName()));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public ArrayList<BreedStruct> getBreeds(){
		return this.breeds;
	}
	
	public void setBreeds(ArrayList<BreedStruct> breeds) {
		this.breeds = breeds;
	}
}
