package com.john.price.PetAdoption.Services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

public class CatResponseMapperTest {
	
	private static List<Cat> cats;
	private static Cat daisy;
	private static PetWithBreedsResponse daisyResponse;
	private static List<PetWithBreedsResponse> catsResponse;
	
	private static void makeCats() {
		daisy = new Cat();
		daisy.setId(1);
		daisy.setName("Daisy");
		
		CatBreed amShort = new CatBreed();
		amShort.setId(1);
		amShort.setName("American Shorthair");
		Set<Cat> catSet = new HashSet<Cat>();
		catSet.add(daisy);
		amShort.setCats(catSet);
		
		Set<Breed> breeds = new HashSet<Breed>();
		breeds.add(amShort);		
		daisy.setBreeds(breeds);
		
		cats = new ArrayList<Cat>();
		cats.add(daisy);
	}
	
	private static void makeCatResponses() {
		daisyResponse = new PetWithBreedsResponse(daisy.getId(), daisy.getName(), daisy.getBreeds());
		catsResponse = new ArrayList<PetWithBreedsResponse>();
		catsResponse.add(daisyResponse);
	}
	
	private static boolean comparePetResponses(PetWithBreedsResponse petResp1, PetWithBreedsResponse petResp2) {
		if(!petResp1.getId().equals(petResp2.getId())) {
			return false;
		}
		if(petResp1.getBreeds().size() != petResp2.getBreeds().size()) {
			return false;
		}
		for(int i = 0; i < petResp1.getBreeds().size(); i++) {
			if(!petResp1.getBreeds().get(i).getId().equals(petResp2.getBreeds().get(i).getId())) {
				return false;
			}
		}
		return true;
	}
	
	@BeforeClass
	public static void beforeTests() {
		makeCats();
		makeCatResponses();
	}
		
	@Test
	public void getCatsResponse() {
		CatResponseMapper catResponseMapper = mock(CatResponseMapper.class);
		when(catResponseMapper.getAllPets()).thenReturn(cats);
		when(catResponseMapper.mapPets()).thenCallRealMethod();
		List<PetWithBreedsResponse> petsResponse = (List<PetWithBreedsResponse>) catResponseMapper.mapPets();
		assertEquals(petsResponse.size(), catsResponse.size());
		PetWithBreedsResponse tempPet1;
		PetWithBreedsResponse tempPet2;
		for(int i = 0; i < petsResponse.size(); i++) {
			tempPet1 = petsResponse.get(i);
			tempPet2 = catsResponse.get(i);
			assertTrue(comparePetResponses(tempPet1, tempPet2));
		}
	}
	
	@Test
	public void getCatResponse() {
		CatResponseMapper catResponseMapper = mock(CatResponseMapper.class);
		when(catResponseMapper.getPet(1)).thenReturn(daisy);
		when(catResponseMapper.mapPet(1)).thenCallRealMethod();
		PetWithBreedsResponse petResponse = catResponseMapper.mapPet(1);
		assertTrue(comparePetResponses(petResponse, daisyResponse));
	}
	
	
}
