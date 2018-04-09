package com.john.price.PetAdoption.Services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.TestHelpers.Assertions;
import com.john.price.PetAdoption.TestHelpers.Builders;

public class DogResponseMapperTest {
	
	private static List<Dog> dogs;
	private static Dog labby;
	private static PetWithBreedsResponse labbyResponse;
	private static List<PetWithBreedsResponse> dogsResponse;
	private static DogResponseMapper responseMapper;
	
	private static void makePets() {	
		labby = Builders.buildDogWithBreedsWithoutDogs();	
		dogs = new ArrayList<Dog>();
		dogs.add(labby);
	}
	
	private static void makePetResponses() {
		labbyResponse = new PetWithBreedsResponse(labby);
		dogsResponse = new ArrayList<PetWithBreedsResponse>();
		dogsResponse.add(labbyResponse);
	}
	
	@SuppressWarnings("unchecked")
	private static void mockMapper() {
		responseMapper = mock(DogResponseMapper.class);
		when(responseMapper.getAllPets()).thenReturn(dogs);
		when(responseMapper.mapPets()).thenCallRealMethod();
		when(responseMapper.getPet(1)).thenReturn(labby);
		when(responseMapper.mapPet(1)).thenCallRealMethod();
		when(responseMapper.instantiatePetWithBreeds()).thenReturn(new Dog());
		doReturn(Builders.buildDogBreedsWithoutDogs()).when(responseMapper).getBreedsFromListOfIds(anyList(), (PetWithBreeds) any());
		when(responseMapper.addPetWithBreedsToBreed((PetWithBreeds)any(), (Breed)any())).thenCallRealMethod();
		when(responseMapper.savePetWithBreeds((PetWithBreeds) any())).thenReturn((PetWithBreeds)Builders.buildDogWithBreedsWithDogs());
		when(responseMapper.createPetWithBreeds(labbyResponse)).thenCallRealMethod();
	}
	
	@BeforeClass
	public static void beforeTests() {
		makePets();
		makePetResponses();
		mockMapper();
	}
		
	@Test
	public void getDogsResponse() {
		List<PetWithBreedsResponse> petsResponse = (List<PetWithBreedsResponse>) responseMapper.mapPets();
		Assertions.assertPetsWithBreedsListsAreEqual(petsResponse, dogsResponse);
	}
	
	@Test
	public void getDogResponse() {
		PetWithBreedsResponse petResponse = responseMapper.mapPet(1);
		Assertions.assertPetsWithBreedsAreEqual(petResponse, labbyResponse);
	}
	
	@Test
	public void createDog() {
		responseMapper.createPetWithBreeds(labbyResponse);
		
		ArgumentCaptor<PetWithBreeds> argument = ArgumentCaptor.forClass(PetWithBreeds.class);
		verify(responseMapper).savePetWithBreeds(argument.capture());
		assertEquals("Labby",argument.getValue().getName());
	}
	
}
