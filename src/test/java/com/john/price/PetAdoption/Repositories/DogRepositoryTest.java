package com.john.price.PetAdoption.Repositories;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.john.price.PetAdoption.Models.Breed;
import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Models.DogBreed;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DogRepositoryTest {
	private Dog spike = new Dog();
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private DogRepository dogRepository;
	
	@Before
	public void initDb() {
		spike.setName("daisy");
		Set<Breed> breeds = new HashSet<Breed>();
		breeds.add(new DogBreed());
		spike.setBreeds(breeds);
		entityManager.persist(spike);
		entityManager.flush();
	}
	
	@After
	public void tearDownDb() {
		entityManager.remove(spike);
	}
	
	@Test
	public void findAllCatsTest() {		
		ArrayList<Dog> dogs = (ArrayList<Dog>)dogRepository.findAll();
		assertTrue(dogs.get(0).getName().equals(spike.getName()));
	}
	
	@Test
	public void findCatByIdTest() {
		Dog dog = dogRepository.findOne(1);
		assertTrue(dog.getName().equals(spike.getName()));
	}
}
