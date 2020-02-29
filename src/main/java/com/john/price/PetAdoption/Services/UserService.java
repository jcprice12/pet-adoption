package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Models.ApplicationUser;
import com.john.price.PetAdoption.Repositories.ApplicationUserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

  @Autowired private ApplicationUserRepository applicationUserRepository;

  @Autowired private BCryptPasswordEncoder passwordEncoder;

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JWTService jwtService;

  public String authenticateUser(ApplicationUser applicationUser) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                applicationUser.getUsername(), applicationUser.getPassword(), new ArrayList<>()));
    return jwtService.createToken(authentication);
  }

  public void addApplicationUser(ApplicationUser appUser) {
    appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
    applicationUserRepository.save(appUser);
  }
}
