package com.john.price.PetAdoption.Services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.TestHelpers.Assertions;
import com.john.price.PetAdoption.TestHelpers.Builders;

public class CatResponseMapperTest {
	
	private static List<Cat> cats;
	private static Cat daisy;
	private static PetWithBreedsResponse daisyResponse;
	private static List<PetWithBreedsResponse> catsResponse;
	private static CatResponseMapper responseMapper;
	
	private static void makePets() {
		daisy = Builders.buildPlainCat();
		cats = new ArrayList<Cat>();
		cats.add(daisy);
	}
	
	private static void makePetResponses() {
		daisyResponse = new PetWithBreedsResponse(daisy);
		catsResponse = new ArrayList<PetWithBreedsResponse>();
		catsResponse.add(daisyResponse);
	}
	
	@SuppressWarnings("unchecked")
	private static void mockMapper() {
		responseMapper = mock(CatResponseMapper.class);
		when(responseMapper.getAllPets()).thenReturn(cats);
		when(responseMapper.mapPets()).thenCallRealMethod();
		when(responseMapper.getPet(1)).thenReturn(daisy);
		when(responseMapper.mapPet(1)).thenCallRealMethod();
		when(responseMapper.instantiatePetWithBreeds()).thenReturn(new Cat());
		doReturn(Builders.buildCatBreedsWithoutCats()).when(responseMapper).getBreedsFromListOfIds(anyList(), (PetWithBreeds) any());
		when(responseMapper.createPetWithBreeds(daisyResponse)).thenCallRealMethod();
	}
	
	@BeforeClass
	public static void beforeTests() {
		makePets();
		makePetResponses();
		mockMapper();
	}
		
	@Test
	public void getCatsResponse() {
		List<PetWithBreedsResponse> petsResponse = (List<PetWithBreedsResponse>) responseMapper.mapPets();
		Assertions.assertPetsWithBreedsListsAreEqual(petsResponse, catsResponse);
	}
	
	@Test
	public void getCatResponse() {
		PetWithBreedsResponse petResponse = responseMapper.mapPet(1);
		Assertions.assertPetsWithBreedsAreEqual(petResponse, daisyResponse);
	}
	
	@Test
	public void createCat() {
		responseMapper.createPetWithBreeds(daisyResponse);
		
		ArgumentCaptor<PetWithBreeds> argument = ArgumentCaptor.forClass(PetWithBreeds.class);
		verify(responseMapper).savePetWithBreeds(argument.capture());
		assertEquals("Daisy",argument.getValue().getName());
	}
}
