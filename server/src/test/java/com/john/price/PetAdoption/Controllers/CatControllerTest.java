package com.john.price.PetAdoption.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Services.PetWithBreedsResponseMapper;

@RunWith(MockitoJUnitRunner.class)
public class CatControllerTest {
	
	@InjectMocks
	private CatController catController;
	
	@Mock
	private PetWithBreedsResponseMapper mapper;
	
	Cat cat;
	private Iterable<Cat> cats;
	
	@Before
	public void beforeEachTest() {
		cat = new Cat();
		cats = new ArrayList<Cat>();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMapperGetsAllCatsWhenCatsEndpointIsHit() {
		doReturn(cats).when(mapper).mapPets();
		
		Iterable<Cat> catsResponses = (Iterable<Cat>) catController.getCats();
		
		verify(mapper).mapPets();
		assertEquals(cats, catsResponses);
	}
	
	@Test
	public void testMapperGetsACatWhenCatEndpointIsHitWithAnId() {
		doReturn(cat).when(mapper).mapPet(1);
		
		Cat catResponse = (Cat) catController.getCat(1);
		
		verify(mapper).mapPet(1);
		assertEquals(cat, catResponse);
	}
	
	@Test
	public void testMapperCreatesACatWhenPostCatsEndpointIsHit() {
		Cat catRequest = new Cat();
		doReturn(cat).when(mapper).createPetWithBreeds(catRequest);
		
		Cat catResponse = (Cat) catController.createCat(catRequest);
		
		verify(mapper).createPetWithBreeds(cat);
		assertEquals(cat, catResponse);
	}
	
	@Test
	public void testMapperEditsACatWhenPutCatsEndpointIsHit() {
		Cat catRequest = new Cat();
		doReturn(cat).when(mapper).editPetWithBreeds(catRequest);
		
		Cat catResponse = (Cat) catController.editCat(catRequest);
		
		verify(mapper).editPetWithBreeds(cat);
		assertEquals(cat, catResponse);
	}
}
