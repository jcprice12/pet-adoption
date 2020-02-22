package com.john.price.PetAdoption.Models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Breed {
	
	@NotNull
	protected Integer id;
	
	@NotNull
	protected String name;
	
	public Breed() {}
	
	public Breed(Breed breed) {
		this.id = breed.id;
		this.name = breed.name;
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "breed_id")
	public Integer getId() {
		return id;
	}	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
