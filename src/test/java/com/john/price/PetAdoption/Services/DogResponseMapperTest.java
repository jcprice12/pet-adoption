package com.john.price.PetAdoption.Services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.TestHelpers.Comparisons;

public class DogResponseMapperTest {
	private static List<Dog> dogs;
	private static Dog labby;
	private static PetWithBreedsResponse labbyResponse;
	private static List<PetWithBreedsResponse> dogsResponse;
	private static DogResponseMapper responseMapper;
	
	private static void makePets() {
		labby = new Dog();
		labby.setId(1);
		labby.setName("Labby");
		
		DogBreed lab = new DogBreed();
		lab.setId(1);
		lab.setName("Labrador Retriever");
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
		labbyResponse = new PetWithBreedsResponse(labby.getId(), labby.getName(), labby.getBreeds());
		dogsResponse = new ArrayList<PetWithBreedsResponse>();
		dogsResponse.add(labbyResponse);
	}
	
	private static void mockMapper() {
		responseMapper = mock(DogResponseMapper.class);
		when(responseMapper.getAllPets()).thenReturn(dogs);
		when(responseMapper.mapPets()).thenCallRealMethod();
		when(responseMapper.getPet(1)).thenReturn(labby);
		when(responseMapper.mapPet(1)).thenCallRealMethod();
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
		Comparisons.comparePetsResponses(petsResponse, dogsResponse);
	}
	
	@Test
	public void getDogResponse() {
		PetWithBreedsResponse petResponse = responseMapper.mapPet(1);
		Comparisons.comparePetResponses(petResponse, labbyResponse);
	}
}
