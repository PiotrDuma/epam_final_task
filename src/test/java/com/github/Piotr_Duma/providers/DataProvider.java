package com.github.Piotr_Duma.providers;

import com.github.Piotr_Duma.utils.StringGenerator;
import com.github.Piotr_Duma.utils.User;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class DataProvider {

  public static Stream<Arguments> getValidUserCredentials() {
    return Stream.of(
      Arguments.of(new User("standard_user", "secret_sauce")),
      Arguments.of(new User("problem_user", "secret_sauce")),
      Arguments.of(new User("performance_glitch_user", "secret_sauce")),
      Arguments.of(new User("visual_user", "secret_sauce")));
  }

  public static Stream<Arguments> getInvalidUserCredentials() {
    return Stream.of(
      Arguments.of(new User(StringGenerator.generateString(10), StringGenerator.generateString(10))),
      Arguments.of(new User(StringGenerator.generateString(10), "")));
  }
}
