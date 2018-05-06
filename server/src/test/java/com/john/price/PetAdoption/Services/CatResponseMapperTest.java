package com.john.price.PetAdoption.Services;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Repositories.CatBreedRepository;
import com.john.price.PetAdoption.Repositories.CatRepository;
import com.john.price.PetAdoption.TestHelpers.Assertions;
import com.john.price.PetAdoption.TestHelpers.Builders;
import com.john.price.PetAdoption.TestHelpers.Constants;

@RunWith(MockitoJUnitRunner.class)
public class CatResponseMapperTest {
	
	private static List<Cat> cats;
	private static Cat daisy;
	
	@InjectMocks
	private static CatResponseMapper responseMapper;
	
	@Mock
	private static CatRepository catRepository;
	
	@Mock
	private static CatBreedRepository catBreedRepository;	
	
	private static void makePets() {
		daisy = Builders.buildCatWithBreedsWithoutCats();
		cats = new ArrayList<Cat>();
		cats.add(daisy);
	}
	
	private static void mockMyStuff() {
		when(catRepository.findAll()).thenReturn(cats);
		when(catRepository.findOne(Constants.ONE_ID)).thenReturn(daisy);
		when(catRepository.save(daisy)).thenReturn(daisy);
		when(catBreedRepository.findByIdIn(Arrays.asList(Constants.ONE_ID))).thenReturn(Builders.buildCatBreedsWithoutCats());
		when(catBreedRepository.findByCatsId(Constants.ONE_ID)).thenReturn(Builders.buildCatBreedsWithoutCats());
		when(catBreedRepository.save(anyListOf(CatBreed.class))).thenReturn(new ArrayList<CatBreed>());
	}
	
	@Before
	public void setUp() {
		makePets();
		mockMyStuff();
	}
		
	@Test
	public void testMapPetsShouldReturnCats() {
		Assertions.assertPetsWithBreedsListsAreEqual(responseMapper.mapPets(), cats);
	}
	
	@Test
	public void testMapPetShouldReturnCat() {
		Assertions.assertPetsWithBreedsAreEqual(responseMapper.mapPet(1), daisy);
	}
	
	@Test
	public void createCat() {
		PetWithBreeds petWithBreeds = responseMapper.createPetWithBreeds(daisy);		
		Assertions.assertPetsWithBreedsAreEqual(petWithBreeds, daisy);
	}
}
