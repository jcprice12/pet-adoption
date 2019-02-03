package com.john.price.PetAdoption.Controllers;

import static org.mockito.Mockito.doReturn;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;

@RunWith(MockitoJUnitRunner.class)
public class DogControllerTest {
	
	@InjectMocks
	private DogController dogController;
	
	@Mock
	private PetWithBreedsResponseMapper mapper;
	
	Dog dog;
	private Iterable<Dog> dogs;
	
	@Before
	public void beforeEachTest() {
		dog = new Dog();
		dogs = new ArrayList<Dog>();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMapperGetsAllDogsWhenDogsEndpointIsHit() {
		doReturn(dogs).when(mapper).mapPets();
		
		Iterable<Dog> dogsResponse = (Iterable<Dog>) dogController.getDogs();
		
		verify(mapper).mapPets();
		assertEquals(dogs, dogsResponse);
	}
	
	@Test
	public void testMapperGetsADogWhenDogEndpointIsHitWithAnId() {
		doReturn(dog).when(mapper).mapPet(1);
		
		Dog dogResponse = (Dog) dogController.getDog(1);
		
		verify(mapper).mapPet(1);
		assertEquals(dog, dogResponse);
	}
	
	@Test
	public void testMapperCreatesADogWhenPostDogsEndpointIsHit() {
		Dog dogRequest = new Dog();
		doReturn(dog).when(mapper).createPetWithBreeds(dogRequest);
		
		Dog dogResponse = (Dog) dogController.createDog(dogRequest);
		
		verify(mapper).createPetWithBreeds(dog);
		assertEquals(dog, dogResponse);
	}
	
	@Test
	public void testMapperEditsADogWhenPutDogsEndpointIsHit() {
		Dog dogRequest = new Dog();
		doReturn(dog).when(mapper).editPetWithBreeds(dogRequest);
		
		Dog dogResponse = (Dog) dogController.editDog(dogRequest);
		
		verify(mapper).editPetWithBreeds(dog);
		assertEquals(dog, dogResponse);
	}
}
