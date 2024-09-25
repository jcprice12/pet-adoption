package com.john.price.PetAdoption.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.john.price.PetAdoption.Models.Dog;
import com.john.price.PetAdoption.Services.DogService;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DogControllerTest {

    private static final int MOCK_ID = 1;

    private Dog retrievedDog;
    private Dog savedDog;
    private Dog dogRequest;

    @InjectMocks
    private DogController dogController;

    @Mock
    private DogService dogService;

    @BeforeEach
    public void beforeEachTest() {
        retrievedDog = new Dog();
        savedDog = new Dog();
        savedDog.setId(MOCK_ID);
        dogRequest = new Dog();
    }

    @Test
    public void test_service_is_used_to_get_all_pets() {
        ArrayList<Dog> retrievedDogs = new ArrayList<Dog>();
        retrievedDogs.add(retrievedDog);
        doReturn(retrievedDogs).when(dogService).getPets();

        Iterable<Dog> dogsResponse = (Iterable<Dog>) dogController.getPets();

        verify(dogService).getPets();
        assertEquals(retrievedDogs, dogsResponse);
    }

    @Test
    public void test_service_is_used_to_get_a_pet_by_id() {
        doReturn(retrievedDog).when(dogService).getPet(anyInt());

        Dog dogResponse = (Dog) dogController.getPet(MOCK_ID);

        verify(dogService).getPet(MOCK_ID);
        assertEquals(retrievedDog, dogResponse);
    }

    @Test
    public void test_service_is_used_to_create_a_pet() {
        doReturn(savedDog).when(dogService).createPet(any(Dog.class));

        Dog dogResponse = (Dog) dogController.createPet(dogRequest);

        verify(dogService).createPet(dogRequest);
        assertEquals(savedDog, dogResponse);
    }

    @Test
    public void test_service_is_used_to_edit_a_pet() {
        doReturn(savedDog).when(dogService).editPet(any(Dog.class));

        Dog dogResponse = (Dog) dogController.editPet(dogRequest);

        verify(dogService).editPet(dogRequest);
        assertEquals(savedDog, dogResponse);
    }
}
