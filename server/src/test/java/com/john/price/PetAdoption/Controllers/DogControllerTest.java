package com.john.price.PetAdoption.Controllers;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.john.price.PetAdoption.Services.DogResponseMapper;
import com.john.price.PetAdoption.TestHelpers.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class DogControllerTest {
	
	@Autowired
	private DogController dogController;
	
	@MockBean
	private DogResponseMapper dogResponseMapper;

	@Test
	public void testAllOnDogControllerShouldCallMapPets() {
		dogController.getDogs();
		verify(dogResponseMapper).mapPets();
	}
	
	@Test
	public void testByIdOnDogControllerShouldCallMapPet() {
		dogController.getDog(Constants.ONE_ID);
		verify(dogResponseMapper).mapPet(Constants.ONE_ID);
	}
}
