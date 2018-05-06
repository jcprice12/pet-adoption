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
	
	public static final void assertPetsWithBreedsListsAreEqual(List<? extends PetWithBreeds> pets1, List<? extends PetWithBreeds> pets2) {	
		assertEquals(pets1.size(), pets2.size());
		for(int i = 0; i < pets1.size(); i++) {
			assertPetsWithBreedsAreEqual(pets1.get(i), pets2.get(i));
		}
	}
}
