package com.john.price.PetAdoption.Controllers;

import static com.john.price.PetAdoption.Constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.john.price.PetAdoption.Constants.SecurityConstants.BEARER;

import com.john.price.PetAdoption.Models.ApplicationUser;
import com.john.price.PetAdoption.Services.UserService;
import com.john.price.PetAdoption.ValidatorGroups.ApplicationUserApiValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void register(
      @RequestBody
          @Validated({javax.validation.groups.Default.class, ApplicationUserApiValidation.class})
          ApplicationUser appUser) {
    userService.addApplicationUser(appUser);
  }

  @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> login(
      @RequestBody
          @Validated({javax.validation.groups.Default.class, ApplicationUserApiValidation.class})
          ApplicationUser appUser) {
    String token = userService.authenticateUser(appUser);
    return ResponseEntity.ok().header(AUTHORIZATION_HEADER, BEARER + token).build();
  }
}
