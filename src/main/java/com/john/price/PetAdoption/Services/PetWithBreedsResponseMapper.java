package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

public interface PetWithBreedsResponseMapper {
	Iterable<PetWithBreedsResponse> mapPets();
	PetWithBreedsResponse mapPet(Integer id);
}
