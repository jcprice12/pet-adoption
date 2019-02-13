package com.john.price.PetAdoption.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Services.DogService;

@RunWith(MockitoJUnitRunner.class)
public class DogControllerTest {
	
	private static final int MOCK_ID = 1;
	
	private Dog retrievedDog;
	private Dog savedDog;
	private Dog dogRequest;
	private Iterable<Dog> retrievedDogs;
	
	@InjectMocks
	private DogController dogController;
	
	@Mock
	private DogService dogService;
	
	@Before
	public void beforeEachTest() {
		retrievedDog = new Dog();
		savedDog = new Dog();
		savedDog.setId(MOCK_ID);
		dogRequest = new Dog();
		retrievedDogs = new ArrayList<Dog>();
		
		doReturn(retrievedDogs).when(dogService).getPets();
		doReturn(retrievedDog).when(dogService).getPet(anyInt());
		doReturn(savedDog).when(dogService).createPet(any(Dog.class));
		doReturn(savedDog).when(dogService).editPet(any(Dog.class));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMapperGetsAllDogsWhenDogsEndpointIsHit() {	
		Iterable<Dog> dogsResponse = (Iterable<Dog>) dogController.getDogs();
		
		verify(dogService).getPets();
		assertEquals(retrievedDogs, dogsResponse);
	}
	
	@Test
	public void testMapperGetsADogWhenDogEndpointIsHitWithAnId() {		
		Dog dogResponse = (Dog) dogController.getDog(MOCK_ID);
		
		verify(dogService).getPet(MOCK_ID);
		assertEquals(retrievedDog, dogResponse);
	}
	
	@Test
	public void testMapperCreatesADogWhenPostDogsEndpointIsHit() {
		Dog dogResponse = (Dog) dogController.createDog(dogRequest);
		
		verify(dogService).createPet(retrievedDog);
		verify(dogService).getPet(MOCK_ID);
		assertEquals(retrievedDog, dogResponse);
	}
	
	@Test
	public void testMapperEditsADogWhenPutDogsEndpointIsHit() {	
		Dog dogResponse = (Dog) dogController.editDog(dogRequest);
		
		verify(dogService).editPet(retrievedDog);
		verify(dogService).getPet(MOCK_ID);
		assertEquals(retrievedDog, dogResponse);
	}
}
