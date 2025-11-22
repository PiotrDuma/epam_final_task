package com.github.Piotr_Duma.utils;

public class ApplicationPropertyException extends RuntimeException{
  private static final String MESSAGE = "Property '%s' not found exception.";

  public ApplicationPropertyException(String propertyName) {
    super(String.format(MESSAGE, propertyName));
  }
}
