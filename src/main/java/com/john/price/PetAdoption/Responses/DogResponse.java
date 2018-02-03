package com.john.price.PetAdoption.Responses;

import java.util.ArrayList;

import com.john.price.PetAdoption.Models.DogBreed;

public class DogResponse {
	private String name;
	private Integer id;
	private ArrayList<DogBreedStruct> breeds;
	
	public DogResponse() {}
	
	public DogResponse(Integer id, String name, Iterable<DogBreed> breeds) {
		this.setId(id);
		this.setName(name);
		this.breeds = new ArrayList<DogBreedStruct>();
		for(DogBreed breed : breeds) {
			this.breeds.add(new DogBreedStruct(breed.getDogBreedId(), breed.getName()));
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
	
	public ArrayList<DogBreedStruct> getBreeds(){
		return this.breeds;
	}
	
	public void setBreeds(ArrayList<DogBreedStruct> breeds) {
		this.breeds = breeds;
	}
}
