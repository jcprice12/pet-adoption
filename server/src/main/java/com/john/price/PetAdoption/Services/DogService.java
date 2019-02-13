package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.john.price.PetAdoption.Functions.DogToDogWithoutDogsInBreedsMapper;
import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Repositories.DogRepository;

@Component
public class DogService implements PetService<Dog> {
	
	@Autowired
	private DogToDogWithoutDogsInBreedsMapper dogToDogWithoutDogsInBreedsMapper;
	
	@Autowired
	private DogRepository dogRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private void insertIntoIntersectionTable(Dog dog) {
		List<Object[]> parameters = new ArrayList<Object[]>();
	    for (Breed breed : dog.getBreeds()) {
	        parameters.add(new Object[] {breed.getId(), dog.getId()});
	    }
		jdbcTemplate.batchUpdate("INSERT INTO dogbreed_dog (breed_id, dog_id) VALUES (?, ?)", parameters);
	}
	
	@Override
	public Collection<Dog> getPets() {
		return dogRepository.findAll().stream().map(dogToDogWithoutDogsInBreedsMapper).collect(Collectors.toList());
	}

	@Override
	public Dog getPet(Integer id) {
		return dogToDogWithoutDogsInBreedsMapper.apply(dogRepository.findOne(id));
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public Dog createPet(Dog dog) {
		Dog savedDog = dogRepository.save(dog);
		insertIntoIntersectionTable(savedDog);
		return savedDog;
	}

	@Override
	@Transactional
	public Dog editPet(Dog dog) {
		jdbcTemplate.update("DELETE from dogbreed_dog where dog_id = ?", dog.getId());
		return createPet(dog);
	}
}