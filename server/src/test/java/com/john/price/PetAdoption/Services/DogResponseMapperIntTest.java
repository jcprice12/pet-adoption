package com.john.price.PetAdoption.Services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.john.price.PetAdoption.Models.PetWithBreeds;
import com.john.price.PetAdoption.Responses.BreedResponse;
import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogResponseMapperIntTest {
	
	@Autowired
	DogResponseMapper mapper;
	
	@Test
	public void createDogIntegrationTest() {
		BreedResponse breedResponse = new BreedResponse();
		breedResponse.setId(1);
		List<BreedResponse> breeds = new ArrayList<BreedResponse>();
		breeds.add(breedResponse);
		PetWithBreedsResponse petWithBreedsResponse = new PetWithBreedsResponse();
		petWithBreedsResponse.setName("Mario");
		petWithBreedsResponse.setImage("");
		petWithBreedsResponse.setDescription("");
		petWithBreedsResponse.setBreeds(breeds);
		
		PetWithBreeds petWithBreeds = mapper.createPetWithBreeds(petWithBreedsResponse);
		assertNotNull(petWithBreeds.getId());
	}
	
}
