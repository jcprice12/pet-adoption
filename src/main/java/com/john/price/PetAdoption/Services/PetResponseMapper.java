package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Responses.PetResponse;

public interface PetResponseMapper {
	Iterable<PetResponse> mapPets();
	PetResponse mapPet(Integer id);
}
