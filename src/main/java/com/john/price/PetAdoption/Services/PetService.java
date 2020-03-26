package com.john.price.PetAdoption.Services;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;
import com.john.price.PetAdoption.Models.Pet;
import java.util.Collection;
import org.springframework.web.multipart.MultipartFile;

public interface PetService<T extends Pet> {
  public Collection<T> getPets();

  public T getPet(Integer id);

  public T createPet(T t);

  public T editPet(T t);

  public String uploadFile(MultipartFile multipartFile) throws UploadS3FileException;
}
