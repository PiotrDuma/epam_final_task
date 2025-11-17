package com.github.Piotr_Duma.provider;

import com.github.Piotr_Duma.utils.User;

public class UserProvider {
  public static final String USER_LOGIN = "problem_user";
  public static final String USER_PASSWORD = "secret_sauce";

  public static User withValidCredentials(){
    return new User(USER_LOGIN, USER_PASSWORD);
  }

  public static User withEmptyUsername(){
    return new User("", USER_PASSWORD);
  }

  public static User withEmptyPassword(){
    return new User(USER_LOGIN, "");
  }

  public static User emptyCredentials(){
    return new User("", "");
  }
}
