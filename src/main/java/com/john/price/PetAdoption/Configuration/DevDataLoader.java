package com.john.price.PetAdoption.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Pet;
import com.john.price.PetAdoption.Repositories.PetRepository;

@Component
@Profile("dev")
public class DevDataLoader implements ApplicationRunner {

	@Autowired
	private PetRepository petRepository;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		petRepository.save(new Pet("spike"));

	}

}
