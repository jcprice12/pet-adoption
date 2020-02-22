package com.john.price.PetAdoption.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.john.price.PetAdoption.Functions.PetToPetMapper;
import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.PetWithBreeds;

public abstract class PetWithBreedsService<T extends PetWithBreeds> implements PetService<T>{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	protected abstract PetToPetMapper<T, T> getPetToPetMapper();
	protected abstract JpaRepository<T, Integer> getRepository();
	protected abstract String getIntersectionTableName();
	protected abstract String getPetIdColumnName();
	
	private void insertIntoIntersectionTable(T t) {
		List<Object[]> parameters = new ArrayList<Object[]>();
	    for (Breed breed : t.getBreeds()) {
	        parameters.add(new Object[] {breed.getId(), t.getId()});
	    }
		jdbcTemplate.batchUpdate(String.format("INSERT INTO %s (breed_id, %s) VALUES (?, ?)", getIntersectionTableName(), getPetIdColumnName()), parameters);
	}
	
	@Override
	public Collection<T> getPets() {
		return getRepository().findAll().stream().map(getPetToPetMapper()).collect(Collectors.toList());
	}
	
	@Override
	public T getPet(Integer id) {
		return getPetToPetMapper().apply(getRepository().findOne(id));
	}

	@Override
	@Transactional
	public T createPet(T t) {
		T savedPetWithBreeds = getRepository().save(t);
		insertIntoIntersectionTable(savedPetWithBreeds);
		return getPetToPetMapper().apply(savedPetWithBreeds);
	}

	@Override
	@Transactional
	public T editPet(T t) {
		jdbcTemplate.update(String.format("DELETE from %s where %s = ?", getIntersectionTableName(), getPetIdColumnName()), t.getId());
		return getPetToPetMapper().apply(createPet(t));
	}
}
