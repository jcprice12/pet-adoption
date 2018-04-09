package com.john.price.PetAdoption.Services;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.john.price.PetAdoption.Responses.PetWithBreedsResponse;
import com.john.price.PetAdoption.TestHelpers.Builders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class DogResponseMapperIntTest {
	
	@Autowired
	DogResponseMapper mapper;
	
	@Test
	public void createDogIntegrationTest() {
		PetWithBreedsResponse petWithBreedsResponse = mapper.createPetWithBreeds(Builders.buildPlainDogResponse());
		assertNotNull(petWithBreedsResponse.getId());
	}
	
}
