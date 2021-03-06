package com.john.price.PetAdoption.Controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Services.CatService;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatControllerTest {

  private static final int MOCK_ID = 1;

  private Cat retrievedCat;
  private Cat savedCat;
  private Cat catRequest;
  private Iterable<Cat> retrievedCats;

  @InjectMocks private CatController catController;

  @Mock private CatService catService;

  @Before
  public void beforeEachTest() {
    retrievedCat = new Cat();
    savedCat = new Cat();
    savedCat.setId(MOCK_ID);
    catRequest = new Cat();
    retrievedCats = new ArrayList<Cat>();

    doReturn(retrievedCats).when(catService).getPets();
    doReturn(retrievedCat).when(catService).getPet(anyInt());
    doReturn(savedCat).when(catService).createPet(any(Cat.class));
    doReturn(savedCat).when(catService).editPet(any(Cat.class));
  }

  @Test
  public void test_service_is_used_to_get_all_pets() {
    Iterable<Cat> catsResponses = (Iterable<Cat>) catController.getPets();

    verify(catService).getPets();
    assertEquals(retrievedCats, catsResponses);
  }

  @Test
  public void test_service_is_used_to_get_a_pet_by_id() {
    Cat catResponse = (Cat) catController.getPet(1);

    verify(catService).getPet(MOCK_ID);
    assertEquals(retrievedCat, catResponse);
  }

  @Test
  public void test_service_is_used_to_create_a_pet() {
    Cat catResponse = (Cat) catController.createPet(catRequest);

    verify(catService).createPet(catRequest);
    assertEquals(savedCat, catResponse);
  }

  @Test
  public void test_service_is_used_to_edit_a_pet() {
    Cat catResponse = (Cat) catController.editPet(catRequest);

    verify(catService).editPet(catRequest);
    assertEquals(savedCat, catResponse);
  }
}
