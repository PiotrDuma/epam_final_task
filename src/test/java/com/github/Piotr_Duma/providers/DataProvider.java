package com.github.Piotr_Duma.providers;

import com.github.Piotr_Duma.utils.StringGenerator;
import com.github.Piotr_Duma.utils.User;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class DataProvider {

  public static Stream<Arguments> getValidUserCredentials() {
    Stream.Builder<Arguments> builder = Stream.builder();
    builder.add(Arguments.of(new User("standard_user", "secret_sauce")));
//    builder.add(Arguments.of(new User("locked_out_user", "secret_sauce"))); //invalid login
    builder.add(Arguments.of(new User("problem_user", "secret_sauce")));
    builder.add(Arguments.of(new User("performance_glitch_user", "secret_sauce")));
//    builder.add(Arguments.of(new User("error_user", "secret_sauce"))); //invalid login
    builder.add(Arguments.of(new User("visual_user", "secret_sauce")));
    return builder.build();
  }

  public static Stream<Arguments> getInvalidUserCredentials() {
    Stream.Builder<Arguments> builder = Stream.builder();
    builder.add(Arguments.of(new User(StringGenerator.generateString(10), StringGenerator.generateString(10))));
    builder.add(Arguments.of(new User(StringGenerator.generateString(10), StringGenerator.generateString(10))));
    builder.add(Arguments.of(new User(StringGenerator.generateString(10), StringGenerator.generateString(10))));
    return builder.build();
  }
}
