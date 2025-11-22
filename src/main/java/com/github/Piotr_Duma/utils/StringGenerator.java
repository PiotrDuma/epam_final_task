package com.github.Piotr_Duma.utils;

import java.util.Random;

/**
 * Random string generator
 */
public class StringGenerator {
  private static final String ALPHANUMERIC_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  /**
   * Get a random string with given length
   *
   * @param length of the string
   * @return random string with given length
   */
  public static String generateString(int length) {
    Random random = new Random();
    StringBuilder builder = new StringBuilder(length);
    for(int i = 0; i<length; i++){
      builder.append(ALPHANUMERIC_STRING.charAt(random.nextInt(ALPHANUMERIC_STRING.length())));
    }
  return builder.toString();
  }
}
