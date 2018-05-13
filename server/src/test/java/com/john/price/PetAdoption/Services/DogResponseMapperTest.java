package com.john.price.PetAdoption.Services;

import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Repositories.DogBreedRepository;
import com.john.price.PetAdoption.Repositories.DogRepository;
import com.john.price.PetAdoption.TestHelpers.Assertions;
import com.john.price.PetAdoption.TestHelpers.Builders;
import com.john.price.PetAdoption.TestHelpers.Constants;

@RunWith(MockitoJUnitRunner.class)
public class DogResponseMapperTest {
	
	private static List<Dog> dogs;
	private static Dog labby;
	
	@InjectMocks
	private static DogResponseMapper responseMapper;
	
	@Mock
	private static DogRepository dogRepository;
	
	@Mock
	private static DogBreedRepository dogBreedRepository;
	
	private static void makePets() {	
		labby = Builders.buildDogWithBreedsWithoutDogs();	
		dogs = new ArrayList<Dog>();
		dogs.add(labby);
	}
	
	private static void mockMyStuff() {
		when(dogRepository.findAll()).thenReturn(dogs);
		when(dogRepository.findOne(Constants.ONE_ID)).thenReturn(labby);
		when(dogRepository.save(labby)).thenReturn(labby);
		Set<Integer> ids = new HashSet<Integer>();
		ids.add(Constants.ONE_ID);
		when(dogBreedRepository.findByIdIn(ids)).thenReturn(Builders.buildDogBreedsWithoutDogs());
		when(dogBreedRepository.findByDogsId(Constants.ONE_ID)).thenReturn(Builders.buildDogBreedsWithoutDogs());
		when(dogBreedRepository.save(anySetOf(DogBreed.class))).thenReturn(new HashSet<DogBreed>());
	}
	
	@Before
	public void beforeTests() {
		makePets();
		mockMyStuff();
	}
		
	@Test
	public void testMapPetsShouldReturnDogs() {
		Assertions.assertPetsWithBreedsListsAreEqual(responseMapper.mapPets(), dogs);
	}
	
	@Test
	public void testMapPetShouldReturnDog() {
		Assertions.assertPetsWithBreedsAreEqual(responseMapper.mapPet(1), labby);
	}
	
	@Test
	public void testCreateDog() {
		PetWithBreeds petWithBreeds = responseMapper.createPetWithBreeds(labby);		
		Assertions.assertPetsWithBreedsAreEqual(petWithBreeds, labby);
	}
	
}
