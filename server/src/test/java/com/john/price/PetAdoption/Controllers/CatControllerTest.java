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

import com.john.price.PetAdoption.Services.CatResponseMapper;
import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;
import com.john.price.PetAdoption.TestHelpers.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CatControllerTest {
	
	@Autowired
	private CatController controller;
	
	private CatResponseMapper catResponseMapper;
	private CatController catController;
	
	@Before
	public void setUp() {
		catResponseMapper = mock(CatResponseMapper.class);
		catController = spy(CatController.class);
		when(catController.getMapper()).thenReturn(catResponseMapper);
	}
	
	@Test
	public void getMapperShouldReturnCatResponseMapper(){
		PetWithBreedsResponseMapper mapper = controller.getMapper();
		assertTrue(mapper instanceof CatResponseMapper);
	}
	
	@Test
	public void testAllOnCatControllerShouldCallMapPets() {
		catController.all();
		verify(catResponseMapper).mapPets();
	}
	
	@Test
	public void testByIdOnCatControllerShouldCallMapPet() {
		catController.byId(Constants.ONE_ID);
		verify(catResponseMapper).mapPet(Constants.ONE_ID);
	}
}
