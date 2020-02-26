package com.john.price.PetAdoption.Configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.ApplicationUser;
import com.john.price.PetAdoption.Models.Role;
import com.john.price.PetAdoption.Repositories.ApplicationUserRepository;
import com.john.price.PetAdoption.Repositories.RoleRepository;

@Component
@Profile("dev")
public class DevDataApplicationRunner implements ApplicationRunner{

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private void addAdminUsers() {
		Role adminRole = new Role();
		adminRole.setName("ADMIN");
		roleRepository.save(adminRole);
		
		ApplicationUser admin1 = new ApplicationUser();
		admin1.setPassword(bcryptPasswordEncoder.encode("password"));
		admin1.setUsername("mradmin");
		
		Set<Role> admin1Roles = new HashSet<>();
		admin1Roles.add(adminRole);
		admin1.setRoles(admin1Roles);
		
		applicationUserRepository.save(admin1);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		addAdminUsers();
	}
}
