package com.john.price.PetAdoption.Models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Breed {
	
	protected Integer id;
	protected String name;
	
	public Breed() {}
	
	public Breed(String name) {
		this.name = name;
	}
	
	public Breed(Integer id, String name) {
		this.id = id;
		this.name = name;
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
	
	@Column(nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
