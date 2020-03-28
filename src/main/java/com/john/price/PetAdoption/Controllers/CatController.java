package com.john.price.PetAdoption.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Services.CatService;
import com.john.price.PetAdoption.Services.PetService;

@RestController
@RequestMapping(path = "/cats")
public class CatController extends PetController<Cat> {

  @Autowired private CatService catService;

  @Override
  protected PetService<Cat> getService() {
    return catService;
  }
}
