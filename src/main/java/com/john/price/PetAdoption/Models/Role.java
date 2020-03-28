package com.john.price.PetAdoption.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

  private static String ROLE_PREFIX = "ROLE_";
  private Integer id;
  private Set<ApplicationUser> applicationUsers;
  private String name;

  public Role() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @ManyToMany(mappedBy = "roles")
  public Set<ApplicationUser> getApplicationUsers() {
    return applicationUsers;
  }

  public void setApplicationUsers(Set<ApplicationUser> applicationUsers) {
    this.applicationUsers = applicationUsers;
  }

  @Column(nullable = false, unique = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    String upperCaseRoleName = name.toUpperCase();
    this.name =
        upperCaseRoleName.startsWith(ROLE_PREFIX)
            ? upperCaseRoleName
            : ROLE_PREFIX + upperCaseRoleName;
  }
}
