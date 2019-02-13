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

import com.john.price.PetAdoption.Functions.CatToCatWithoutCatsInBreedsMapper;
import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Repositories.CatRepository;

@Component
public class CatService implements PetService<Cat>{
	
	@Autowired
	private CatToCatWithoutCatsInBreedsMapper catToCatWithoutCatsInBreedsMapper;
	
	@Autowired
	private CatRepository catRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private void insertIntoIntersectionTable(Cat cat) {
		List<Object[]> parameters = new ArrayList<Object[]>();
	    for (Breed breed : cat.getBreeds()) {
	        parameters.add(new Object[] {breed.getId(), cat.getId()});
	    }
		jdbcTemplate.batchUpdate("INSERT INTO catbreed_cat (breed_id, cat_id) VALUES (?, ?)", parameters);
	}

	@Override
	public Collection<Cat> getPets() {		
		return catRepository.findAll().stream().map(catToCatWithoutCatsInBreedsMapper).collect(Collectors.toList());
	}

	@Override
	public Cat getPet(Integer id) {
		return catToCatWithoutCatsInBreedsMapper.apply(catRepository.findOne(id));
	}
	
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public Cat createPet(Cat cat) {
		Cat savedCat = catRepository.save(cat);
		insertIntoIntersectionTable(savedCat);
		return savedCat;
	}
	
	@Override
	@Transactional
	public Cat editPet(Cat cat) {
		jdbcTemplate.update("DELETE from catbreed_cat where cat_id = ?", cat.getId());
		return createPet(cat);
	}
}