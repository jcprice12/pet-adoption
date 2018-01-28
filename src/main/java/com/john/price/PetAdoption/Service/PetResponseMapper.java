package com.john.price.PetAdoption.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Pet;
import com.john.price.PetAdoption.Repositories.PetRepository;
import com.john.price.PetAdoption.Response.PetResponse;

@Component
public class PetResponseMapper {
	@Autowired 
	private PetRepository petRepository;
	
	public Iterable<PetResponse> mapPets() {
		Iterable<Pet> data = petRepository.findAll();
		List<PetResponse> pets = new ArrayList<PetResponse>();
		for(Pet pet : data) {
			pets.add(new PetResponse(pet.getPetId(), pet.getName(), pet.getBreeds()));
		}
		return pets;
	}
	
	public PetResponse mapPet(Integer id) {
		Pet data = petRepository.findOne(id);
		return new PetResponse(data.getPetId(), data.getName(), data.getBreeds());
	}
}
