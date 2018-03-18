package com.john.price.PetAdoption.Services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.TestHelpers.Comparisons;

public class CatResponseMapperTest {
	
	private static List<Cat> cats;
	private static Cat daisy;
	private static PetWithBreedsResponse daisyResponse;
	private static List<PetWithBreedsResponse> catsResponse;
	private static CatResponseMapper responseMapper;
	
	private static void makePets() {
		
		daisy = new Cat(1, "Daisy", "url", "A very friendly pet");	
		CatBreed amShort = new CatBreed(1, "American Shorthair");
		
		Set<Cat> catSet = new HashSet<Cat>();
		catSet.add(daisy);
		amShort.setCats(catSet);	
		Set<Breed> breeds = new HashSet<Breed>();
		breeds.add(amShort);		
		daisy.setBreeds(breeds);	
		cats = new ArrayList<Cat>();
		cats.add(daisy);
	}
	
	private static void makePetResponses() {
		daisyResponse = new PetWithBreedsResponse(daisy.getId(), daisy.getName(), daisy.getBreeds());
		catsResponse = new ArrayList<PetWithBreedsResponse>();
		catsResponse.add(daisyResponse);
	}
	
	private static void mockMapper() {
		responseMapper = mock(CatResponseMapper.class);
		when(responseMapper.getAllPets()).thenReturn(cats);
		when(responseMapper.mapPets()).thenCallRealMethod();
		when(responseMapper.getPet(1)).thenReturn(daisy);
		when(responseMapper.mapPet(1)).thenCallRealMethod();
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
		Comparisons.comparePetsResponses(petsResponse, catsResponse);
	}
	
	@Test
	public void getCatResponse() {
		PetWithBreedsResponse petResponse = responseMapper.mapPet(1);
		Comparisons.comparePetResponses(petResponse, daisyResponse);
	}
	
	
}
