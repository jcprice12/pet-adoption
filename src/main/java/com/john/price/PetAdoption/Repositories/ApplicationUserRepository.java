package com.john.price.PetAdoption.Repositories;

import com.john.price.PetAdoption.Models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
  public ApplicationUser findByUsername(String username);
}
