package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Repositories.CatRepository;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

@Component("CatResponseMapper")
public class CatResponseMapper implements PetWithBreedsResponseMapper {

	@Autowired
	CatRepository repository;
	
	@Override
	public Iterable<PetWithBreedsResponse> mapPets() {
		Iterable<Cat> data = repository.findAll();
		List<PetWithBreedsResponse> cats = new ArrayList<PetWithBreedsResponse>();
		for(Cat cat : data) {
			cats.add(new PetWithBreedsResponse(cat, cat.getBreeds()));
		}
		return cats;
	}

	@Override
	public PetWithBreedsResponse mapPet(Integer id) {
		Cat data = repository.findOne(id);
		return new PetWithBreedsResponse(data, data.getBreeds());
	}

}
