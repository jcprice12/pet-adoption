package com.john.price.PetAdoption.Services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.TestHelpers.Comparisons;

public class DogResponseMapperTest {
	
	private static List<Dog> dogs;
	private static Dog labby;
	private static PetWithBreedsResponse labbyResponse;
	private static List<PetWithBreedsResponse> dogsResponse;
	private static DogResponseMapper responseMapper;
	
	private static void makePets() {	
		labby = new Dog(1, "Labby", "url", "A very friendly pet");	
		DogBreed lab = new DogBreed(1, "Labrador Retriever");		
		Set<Dog> dogSet = new HashSet<Dog>();
		dogSet.add(labby);
		lab.setDogs(dogSet);	
		Set<Breed> breeds = new HashSet<Breed>();
		breeds.add(lab);		
		labby.setBreeds(breeds);	
		dogs = new ArrayList<Dog>();
		dogs.add(labby);
	}
	
	private static void makePetResponses() {
		labbyResponse = new PetWithBreedsResponse(labby);
		dogsResponse = new ArrayList<PetWithBreedsResponse>();
		dogsResponse.add(labbyResponse);
	}
	
	private static void mockMapper() {
		responseMapper = mock(DogResponseMapper.class);
		when(responseMapper.getAllPets()).thenReturn(dogs);
		when(responseMapper.mapPets()).thenCallRealMethod();
		when(responseMapper.getPet(1)).thenReturn(labby);
		when(responseMapper.mapPet(1)).thenCallRealMethod();
		when(responseMapper.instantiatePetWithBreeds()).thenReturn(new Dog());
		when(responseMapper.instantiateBreed()).thenReturn(new DogBreed());
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
		Comparisons.comparePetsWithBreedsResponses(petsResponse, dogsResponse);
	}
	
	@Test
	public void getDogResponse() {
		PetWithBreedsResponse petResponse = responseMapper.mapPet(1);
		Comparisons.comparePetWithBreedsResponses(petResponse, labbyResponse);
	}
	
	@Test
	public void createDog() {
		responseMapper.createPetWithBreeds(labbyResponse);
		
		ArgumentCaptor<PetWithBreeds> argument = ArgumentCaptor.forClass(PetWithBreeds.class);
		verify(responseMapper).savePetWithBreeds(argument.capture());
		assertEquals("Labby",argument.getValue().getName());
	}
	
}
