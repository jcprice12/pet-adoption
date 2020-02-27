package com.john.price.PetAdoption.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.Fish;
import com.john.price.PetAdoption.Repositories.FishRepository;

@Component
public class FishService extends PetWithSpeciesService<Fish> {

	@Autowired
	private FishRepository fishRepository;

	@Override
	protected JpaRepository<Fish, Integer> getRepository() {
		return fishRepository;
	}

}
