package com.john.price.PetAdoption.Controllers;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.john.price.PetAdoption.Services.CatResponseMapper;
import com.john.price.PetAdoption.TestHelpers.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CatControllerTest {
	
	@Autowired
	private CatController catController;
	
	@MockBean
	private CatResponseMapper catResponseMapper;
	
	@Test
	public void testAllOnCatControllerShouldCallMapPets() {
		catController.getCats();
		verify(catResponseMapper).mapPets();
	}
	
	@Test
	public void testByIdOnCatControllerShouldCallMapPet() {
		catController.getCat(Constants.ONE_ID);
		verify(catResponseMapper).mapPet(Constants.ONE_ID);
	}
}
