package com.john.price.PetAdoption.Controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

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
	
	@Test
	public void getMapperShouldReturnCatResponseMapper(){
		PetWithBreedsResponseMapper mapper = controller.getMapper();
		assertTrue(mapper instanceof CatResponseMapper);
	}
	
	@Test
	public void allShouldReturnCats() {
		CatResponseMapper mapper = mock(CatResponseMapper.class);
		when(mapper.mapPets()).thenReturn(Builders.buildPetsWithBreedsResponse(1, "cat"));
		CatController controller = spy(CatController.class);
		when(controller.getMapper()).thenReturn(mapper);
		List<PetWithBreedsResponse> pets = (List<PetWithBreedsResponse>) controller.all();
		verify(mapper, times(1)).mapPets();
		assertEquals(pets.size(), 1);
	}
}
