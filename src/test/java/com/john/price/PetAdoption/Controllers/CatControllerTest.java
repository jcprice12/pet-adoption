package com.john.price.PetAdoption.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.john.price.PetAdoption.Models.Cat;
import com.john.price.PetAdoption.Services.CatService;

@ExtendWith(MockitoExtension.class)
public class CatControllerTest {

    private static final int MOCK_ID = 1;

    private Cat retrievedCat;
    private Cat savedCat;
    private Cat catRequest;

    @InjectMocks
    private CatController catController;

    @Mock
    private CatService catService;

    @BeforeEach
    public void beforeEachTest() {
        retrievedCat = new Cat();
        savedCat = new Cat();
        savedCat.setId(MOCK_ID);
        catRequest = new Cat();
    }

    @Test
    public void test_service_is_used_to_get_all_pets() {
        ArrayList<Cat> retrievedCats = new ArrayList<Cat>();
        retrievedCats.add(retrievedCat);
        doReturn(retrievedCats).when(catService).getPets();

        Iterable<Cat> catsResponses = (Iterable<Cat>) catController.getPets();

        verify(catService).getPets();
        assertEquals(retrievedCats, catsResponses);
    }

    @Test
    public void test_service_is_used_to_get_a_pet_by_id() {
        doReturn(retrievedCat).when(catService).getPet(anyInt());

        Cat catResponse = (Cat) catController.getPet(MOCK_ID);

        verify(catService).getPet(MOCK_ID);
        assertEquals(retrievedCat, catResponse);
    }

    @Test
    public void test_service_is_used_to_create_a_pet() {
        doReturn(savedCat).when(catService).createPet(any(Cat.class));

        Cat catResponse = (Cat) catController.createPet(catRequest);

        verify(catService).createPet(catRequest);
        assertEquals(savedCat, catResponse);
    }

    @Test
    public void test_service_is_used_to_edit_a_pet() {
        doReturn(savedCat).when(catService).editPet(any(Cat.class));

        Cat catResponse = (Cat) catController.editPet(catRequest);

        verify(catService).editPet(catRequest);
        assertEquals(savedCat, catResponse);
    }
}
