package com.john.price.PetAdoption.Services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.john.price.PetAdoption.Models.ApplicationUser;
import com.john.price.PetAdoption.Repositories.ApplicationUserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
		if(applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return User.withUsername(username)
				.accountExpired(false)
				.accountLocked(false)
				.authorities(applicationUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()))
				.credentialsExpired(false)
				.disabled(false)
				.password(applicationUser.getPassword())
				.build();
	}
}
