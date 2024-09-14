package com.john.price.PetAdoption.Services;

import org.springframework.web.multipart.MultipartFile;

import com.john.price.PetAdoption.Exceptions.UploadS3FileException;

public interface IS3Service {
  public String uploadMultipartFileToS3(MultipartFile multipartFile, String keyPrefix) throws UploadS3FileException;
}
