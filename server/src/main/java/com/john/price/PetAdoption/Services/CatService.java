package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Repositories.CatRepository;

@Component
public class CatService extends PetWithBreedsService<Cat>{
	
	@Autowired
	private CatRepository catRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Collection<Cat> getPets() {
		Collection<Cat> cats = catRepository.findAll();
		nullifyCatsInCatBreedsForEachCat(cats);
		return cats;
	}

	@Override
	public Cat getPet(Integer id) {
		Cat cat = catRepository.getOne(id);
		nullifyPetsInBreedForPetWithBreeds(cat);
		return cat;
	}

	@Transactional
	public Cat editPet(Cat cat) {
		jdbcTemplate.update("DELETE from catbreed_cat where cat_id = ?", cat.getId());
		return createNewPet(cat);
	}
	
	@Transactional
	public Cat createNewPet(Cat cat) {
		Cat savedCat = catRepository.save(cat);
		insertIntoIntersectionTable(savedCat);
		return savedCat;
	}
	
	private void insertIntoIntersectionTable(Cat cat) {
		List<Object[]> parameters = new ArrayList<Object[]>();
	    for (Breed breed : cat.getBreeds()) {
	        parameters.add(new Object[] {breed.getId(), cat.getId()});
	    }
		jdbcTemplate.batchUpdate("INSERT INTO catbreed_cat (breed_id, cat_id) VALUES (?, ?)", parameters);
	}
	
	private void nullifyCatsInCatBreedsForEachCat(Collection<Cat> cats) {
		cats.forEach(cat -> nullifyPetsInBreedForPetWithBreeds(cat));
	}
	
	private void nullifyPetsInBreedForPetWithBreeds(Cat cat) {
		cat.getBreeds().forEach(breed -> ((CatBreed) breed).setCats(null));
	}
}
