package com.john.price.PetAdoption.TestHelpers;

import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

public abstract class Comparisons {
	public static final boolean comparePetResponses(PetWithBreedsResponse petResp1, PetWithBreedsResponse petResp2) {
		if(!petResp1.getId().equals(petResp2.getId())) {
			return false;
		}
		if(petResp1.getBreeds().size() != petResp2.getBreeds().size()) {
			return false;
		}
		for(int i = 0; i < petResp1.getBreeds().size(); i++) {
			if(!petResp1.getBreeds().get(i).getId().equals(petResp2.getBreeds().get(i).getId())) {
				return false;
			}
		}
		return true;
	}
}
