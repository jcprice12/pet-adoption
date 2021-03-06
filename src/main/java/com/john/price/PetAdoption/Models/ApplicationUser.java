package com.john.price.PetAdoption.Models;

import com.john.price.PetAdoption.ValidatorGroups.ApplicationUserApiValidation;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "application_user")
public class ApplicationUser {

  @Null(groups = {ApplicationUserApiValidation.class})
  private Integer id;

  @NotNull private String username;

  @NotNull private String password;

  @Null(groups = {ApplicationUserApiValidation.class})
  private Set<Role> roles;

  public ApplicationUser() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "applicationuser_id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(nullable = false, unique = true)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "applicationuser_role",
      joinColumns = @JoinColumn(name = "applicationuser_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
