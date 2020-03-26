package com.john.price.PetAdoption.Exceptions;

public class UploadS3FileException extends Exception {

  private static final long serialVersionUID = 8281402876118540173L;

  public UploadS3FileException(Exception e) {
    super(e);
  }
}
