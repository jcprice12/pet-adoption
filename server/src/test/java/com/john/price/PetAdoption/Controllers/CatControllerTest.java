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
import org.springframework.test.context.junit4.SpringRunner;

import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.Services.CatResponseMapper;
import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;
import com.john.price.PetAdoption.TestHelpers.Builders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatControllerTest {
	
	@Autowired
	CatController controller;
	
	private static final int ID = 1;
	private static final int LENGTH = 1;
	
	CatResponseMapper catResponseMapper;
	CatController catController;
	
	@Before
	public void setUp() {
		catResponseMapper = mock(CatResponseMapper.class);
		when(catResponseMapper.mapPets()).thenReturn(Builders.buildPetsWithBreedsResponse(LENGTH, "cat"));
		when(catResponseMapper.mapPet(ID)).thenReturn(Builders.buildPetWithBreedsResponse("cat", ID));
		
		catController = spy(CatController.class);
		when(catController.getMapper()).thenReturn(catResponseMapper);
	}
	
	@Test
	public void getMapperShouldReturnCatResponseMapper(){
		PetWithBreedsResponseMapper mapper = controller.getMapper();
		assertTrue(mapper instanceof CatResponseMapper);
	}
	
	@Test
	public void allShouldReturnCats() {
		List<PetWithBreedsResponse> pets = (List<PetWithBreedsResponse>) catController.all();
		verify(catResponseMapper, times(1)).mapPets();
		assertEquals(pets.size(), LENGTH);
	}
	
	@Test
	public void byIdShouldReturnACat() {
		PetWithBreedsResponse pet = catController.byId(ID);
		verify(catResponseMapper, times(1)).mapPet(ID);
		assertEquals(pet.getId(), (Integer)ID);
	}
}
