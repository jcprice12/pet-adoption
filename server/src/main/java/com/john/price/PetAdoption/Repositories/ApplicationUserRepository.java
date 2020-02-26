package com.john.price.PetAdoption.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.john.price.PetAdoption.Models.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer>{
	public ApplicationUser findByUsername(String username);
}
