package com.john.price.PetAdoption.Controllers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.john.price.PetAdoption.Services.DogResponseMapper;
import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;
import com.john.price.PetAdoption.TestHelpers.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class DogControllerTest {
	
	@Autowired
	DogController controller;
	
	DogResponseMapper dogResponseMapper;
	DogController dogController;
	
	@Before
	public void setUp() {
		dogResponseMapper = mock(DogResponseMapper.class);
		dogController = spy(DogController.class);
		when(dogController.getMapper()).thenReturn(dogResponseMapper);
	}
	
	@Test
	public void getMapperShouldReturnDogResponseMapper(){
		PetWithBreedsResponseMapper mapper = controller.getMapper();
		assertTrue(mapper instanceof DogResponseMapper);
	}
	
	@Test
	public void testAllOnDogControllerShouldCallMapPets() {
		dogController.all();
		verify(dogResponseMapper).mapPets();
	}
	
	@Test
	public void testByIdOnDogControllerShouldCallMapPet() {
		dogController.byId(Constants.ONE_ID);
		verify(dogResponseMapper).mapPet(Constants.ONE_ID);
	}
}
