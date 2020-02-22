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
	
	@Test
	public void test_service_is_used_to_get_all_pets() {	
		Iterable<Dog> dogsResponse = (Iterable<Dog>) dogController.getPets();
		
		verify(dogService).getPets();
		assertEquals(retrievedDogs, dogsResponse);
	}
	
	@Test
	public void test_service_is_used_to_get_a_pet_by_id() {		
		Dog dogResponse = (Dog) dogController.getPet(MOCK_ID);
		
		verify(dogService).getPet(MOCK_ID);
		assertEquals(retrievedDog, dogResponse);
	}
	
	@Test
	public void test_service_is_used_to_create_a_pet() {
		Dog dogResponse = (Dog) dogController.createPet(dogRequest);
		
		verify(dogService).createPet(retrievedDog);
		assertEquals(savedDog, dogResponse);
	}
	
	@Test
	public void test_service_is_used_to_edit_a_pet() {	
		Dog dogResponse = (Dog) dogController.editPet(dogRequest);
		
		verify(dogService).editPet(retrievedDog);
		assertEquals(savedDog, dogResponse);
	}
}
