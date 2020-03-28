package com.john.price.PetAdoption.Controllers;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;
import com.john.price.PetAdoption.Models.Pet;
import com.john.price.PetAdoption.Services.PetService;
import com.john.price.PetAdoption.ValidatorGroups.PetPostValidation;
import com.john.price.PetAdoption.ValidatorGroups.PetPutValidation;

public abstract class PetController<P extends Pet> {

  protected abstract PetService<P> getService();

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<P> getPets() {
    return getService().getPets();
  }

  @GetMapping(path = "/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public P getPet(@PathVariable("petId") Integer petId) {
    return getService().getPet(petId);
  }

  @PostMapping(
      path = "",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Secured("ROLE_ADMIN")
  public P createPet(
      @RequestBody @Validated({javax.validation.groups.Default.class, PetPostValidation.class})
          P pet) {
    return getService().createPet(pet);
  }

  @PutMapping(
      path = "",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Secured("ROLE_ADMIN")
  public P editPet(
      @RequestBody @Validated({javax.validation.groups.Default.class, PetPutValidation.class})
          P pet) {
    return getService().editPet(pet);
  }

  @PutMapping(
      path = "/images",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  @Secured("ROLE_ADMIN")
  public String uploadImage(@RequestParam("image") MultipartFile file)
      throws UploadS3FileException {
    return getService().uploadFile(file);
  }
}
