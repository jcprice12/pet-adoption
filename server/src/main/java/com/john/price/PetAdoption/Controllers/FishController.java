package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Models.Fish;
import com.john.price.PetAdoption.Services.FishService;
import com.john.price.PetAdoption.Services.PetService;

@RestController
@RequestMapping(path = "/fish")
public class FishController extends PetController<Fish>{

	@Autowired
	private FishService fishService;
	
	@Override
	protected PetService<Fish> getService() {
		return fishService;
	}

}
