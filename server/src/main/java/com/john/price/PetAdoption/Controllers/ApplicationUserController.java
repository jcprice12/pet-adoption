package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Models.ApplicationUser;
import com.john.price.PetAdoption.Services.ApplicationUserService;

@RestController
@RequestMapping(path = "/users")
public class ApplicationUserController {

	@Autowired
	private ApplicationUserService applicationUserService;
	
	@PostMapping("/register")
	public void register(@RequestBody ApplicationUser appUser) {
		applicationUserService.addApplicationUser(appUser);
	}
}
