package com.john.price.PetAdoption.Repositories;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.john.price.PetAdoption.Models.Cat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CatRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	CatRepository catRepository;
	
	@Test
	public void findAllCatsTest() {
		Cat daisy = new Cat();
		daisy.setName("daisy");
		entityManager.persist(daisy);
		entityManager.flush();
		
		ArrayList<Cat> cats = (ArrayList<Cat>)catRepository.findAll();
		assertTrue(cats.get(0).getName().equals(daisy.getName()));
	}
}
