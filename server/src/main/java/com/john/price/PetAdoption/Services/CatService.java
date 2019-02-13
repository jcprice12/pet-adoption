package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Models.CatBreed;
import com.john.price.PetAdoption.Repositories.CatRepository;

@Component
public class CatService extends PetWithBreedsService<Cat>{
	
	private class CatsInCatBreedRemover implements Function<Breed, CatBreed>{

		@Override
		public CatBreed apply(Breed breed) {
			CatBreed catBreed = new CatBreed();
			catBreed.setId(breed.getId());
			catBreed.setName(breed.getName());
			catBreed.setCats(null);
			return catBreed;
		}
		
	}
	
	private class CatResponseMapper implements Function<Cat, Cat>{

		@Override
		public Cat apply(Cat cat) {
			Cat responseCat = new Cat();
			responseCat.setDescription(cat.getDescription());
			responseCat.setId(cat.getId());
			responseCat.setImage(cat.getImage());
			responseCat.setName(cat.getName());
			responseCat.setBreeds(cat.getBreeds().stream().map(catsInCatBreedRemover).collect(Collectors.toSet()));
			return responseCat;
		}
	}
	
	private CatsInCatBreedRemover catsInCatBreedRemover = new CatsInCatBreedRemover();
	private CatResponseMapper catResponseMapper = new CatResponseMapper();
	
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
		return catRepository.findAll().stream().map(catResponseMapper).collect(Collectors.toList());
	}

	@Override
	public Cat getPet(Integer id) {
		return catResponseMapper.apply(catRepository.findOne(id));
	}
	
	@Override
	@Transactional
	public Cat editPet(Cat cat) {
		jdbcTemplate.update("DELETE from catbreed_cat where cat_id = ?", cat.getId());
		return createPet(cat);
	}
	
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public Cat createPet(Cat cat) {
		Cat savedCat = catRepository.save(cat);
		insertIntoIntersectionTable(savedCat);
		return savedCat;
	}
}
