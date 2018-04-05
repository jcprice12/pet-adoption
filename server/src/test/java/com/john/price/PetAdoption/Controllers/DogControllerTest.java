package com.john.price.PetAdoption.Controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.Services.DogResponseMapper;
import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;
import com.john.price.PetAdoption.TestHelpers.Builders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class DogControllerTest {
	
	@Autowired
	DogController controller;
	
	private static final int ID = 1;
	private static final int LENGTH = 1;
	
	DogResponseMapper dogResponseMapper;
	DogController dogController;
	
	@Before
	public void setUp() {
		dogResponseMapper = mock(DogResponseMapper.class);
		when(dogResponseMapper.mapPets()).thenReturn(Builders.buildPetsWithBreedsResponse(LENGTH, "dog"));
		when(dogResponseMapper.mapPet(ID)).thenReturn(Builders.buildPetWithBreedsResponse("dog", ID));
		
		dogController = spy(DogController.class);
		when(dogController.getMapper()).thenReturn(dogResponseMapper);
	}
	
	@Test
	public void getMapperShouldReturnDogResponseMapper(){
		PetWithBreedsResponseMapper mapper = controller.getMapper();
		assertTrue(mapper instanceof DogResponseMapper);
	}
	
	@Test
	public void allShouldReturnDogs() {
		List<PetWithBreedsResponse> pets = (List<PetWithBreedsResponse>) dogController.all();
		verify(dogResponseMapper, times(1)).mapPets();
		assertEquals(pets.size(), LENGTH);
	}
	
	@Test
	public void byIdShouldReturnADog() {
		PetWithBreedsResponse pet = dogController.byId(ID);
		verify(dogResponseMapper, times(1)).mapPet(ID);
		assertEquals(pet.getId(), (Integer)ID);
	}
}
