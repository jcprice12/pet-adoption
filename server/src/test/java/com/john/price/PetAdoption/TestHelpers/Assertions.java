package com.john.price.PetAdoption.TestHelpers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.john.price.PetAdoption.Models.PetWithBreeds;

public abstract class Assertions {
	public static final void assertPetsWithBreedsAreEqual(PetWithBreeds pet1, PetWithBreeds pet2) {
		assertEquals(pet1.getId(), pet2.getId());
		assertEquals(pet1.getName(), pet2.getName());
		assertEquals(pet1.getImage(), pet2.getImage());
		assertEquals(pet1.getBreeds().size(), pet2.getBreeds().size());
		assertEquals(pet1.getDescription(), pet2.getDescription());
		assertEquals(pet1.getBreeds().size(), pet2.getBreeds().size());
	}
	
	@SuppressWarnings("unchecked")
	public static final void assertPetsWithBreedsListsAreEqual(Iterable<? extends PetWithBreeds> pets1, Iterable<? extends PetWithBreeds> pets2) {
		List<PetWithBreeds> pets1List = (List<PetWithBreeds>)pets1;
		List<PetWithBreeds> pets2List = (List<PetWithBreeds>)pets2;
		assertEquals(pets1List.size(), pets2List.size());
		for(int i = 0; i < pets1List.size(); i++) {
			assertPetsWithBreedsAreEqual(pets1List.get(i), pets2List.get(i));
		}
	}
}
