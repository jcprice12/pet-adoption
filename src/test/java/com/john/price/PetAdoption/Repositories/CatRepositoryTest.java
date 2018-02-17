package com.john.price.PetAdoption.Repositories;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
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
	private Cat daisy = new Cat();
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CatRepository catRepository;
	
	@Before
	public void initData() {
		daisy.setName("daisy");
		entityManager.persist(daisy);
		entityManager.flush();
	}
	
	@After
	public void removeData() {
		entityManager.remove(daisy);
	}
	
	@Test
	public void findAllCatsTest() {		
		ArrayList<Cat> cats = (ArrayList<Cat>)catRepository.findAll();
		assertTrue(cats.get(0).getName().equals(daisy.getName()));
	}
	
	@Test
	public void findCatByIdTest() {
		Cat cat = catRepository.findOne(1);
		assertTrue(cat.getName().equals(daisy.getName()));
	}
}
