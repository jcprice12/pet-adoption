package com.john.price.PetAdoption.TestHelpers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

public abstract class Assertions {
	public static final void assertPetsWithBreedsAreEqual(PetWithBreedsResponse petResp1, PetWithBreedsResponse petResp2) {
		assertEquals(petResp1.getId(), petResp2.getId());
		assertEquals(petResp1.getName(), petResp2.getName());
		assertEquals(petResp1.getImage(), petResp2.getImage());
		assertEquals(petResp1.getBreeds().size(), petResp2.getBreeds().size());
		assertEquals(petResp1.getDescription(), petResp2.getDescription());
		assertEquals(petResp1.getBreeds().size(), petResp2.getBreeds().size());
	}
	
	public static final void assertPetsWithBreedsListsAreEqual(List<PetWithBreedsResponse> petsResponse1, List<PetWithBreedsResponse> petsResponse2) {	
		PetWithBreedsResponse tempPet1, tempPet2;
		assertEquals(petsResponse1.size(), petsResponse2.size());
		for(int i = 0; i < petsResponse1.size(); i++) {
			tempPet1 = petsResponse1.get(i);
			tempPet2 = petsResponse2.get(i);
			assertPetsWithBreedsAreEqual(tempPet1, tempPet2);
		}
	}
}
