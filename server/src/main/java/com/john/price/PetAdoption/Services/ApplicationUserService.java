package com.john.price.PetAdoption.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.ApplicationUser;
import com.john.price.PetAdoption.Repositories.ApplicationUserRepository;

@Component
public class ApplicationUserService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void addApplicationUser(ApplicationUser appUser) {
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		applicationUserRepository.save(appUser);
	}
}
