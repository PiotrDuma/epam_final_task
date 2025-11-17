package com.github.Piotr_Duma.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Random string generator
 */
public class StringGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

  /**
   *
   * @param length cannot be greater than 32
   * @return random string with given length
   */
  public static String generateString(int length) {
      byte[] randomBytes = new byte[24];
      secureRandom.nextBytes(randomBytes);
      return base64Encoder.encodeToString(randomBytes).substring(0, length);
  }
}
