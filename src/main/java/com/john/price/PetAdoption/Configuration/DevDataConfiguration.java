package com.john.price.PetAdoption.Configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;
import com.john.price.PetAdoption.Repositories.CatBreedRepository;
import com.john.price.PetAdoption.Repositories.CatRepository;
import com.john.price.PetAdoption.Repositories.DogBreedRepository;
import com.john.price.PetAdoption.Repositories.DogRepository;

@Component
@Profile("dev")
public class DevDataConfiguration implements ApplicationRunner{

	@Autowired
	DogRepository dogRepo;
	
	@Autowired
	CatRepository catRepo;
	
	@Autowired 
	DogBreedRepository dogBreedRepo;
	
	@Autowired 
	CatBreedRepository catBreedRepo;
	
	private void addDogs() {
		Dog labby = new Dog();
		labby.setName("Labby");
		dogRepo.save(labby);
		
		DogBreed lab = new DogBreed();
		lab.setName("LabradorRetriever");
		
		Set<Dog> labDogs = new HashSet<Dog>();
		labDogs.add(labby);		
		lab.setDogs(labDogs);
		
		dogBreedRepo.save(lab);
	}
	
	private void addCats() {
		Cat daisy = new Cat();
		daisy.setName("Daisy");
		catRepo.save(daisy);
		
		CatBreed amShort = new CatBreed();
		amShort.setName("American Shorthair");
		
		Set<Cat> amShortCats = new HashSet<Cat>();
		amShortCats.add(daisy);		
		amShort.setCats(amShortCats);
		
		catBreedRepo.save(amShort);
	}
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		addDogs();
		addCats();	
	}
}
