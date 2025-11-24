package com.github.Piotr_Duma.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.Piotr_Duma.page.LoginPage;
import com.github.Piotr_Duma.providers.WebDriverProvider;
import com.github.Piotr_Duma.utils.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPageTests extends WebDriverProvider {
    private static final Logger log = LoggerFactory.getLogger(LoginPageTests.class);

    //UC-1 test login form with empty credentials
    @ParameterizedTest
    @MethodSource("com.github.Piotr_Duma.providers.DataProvider#getInvalidUserCredentials")
    public void loginFormWithEmptyCredentialsShouldReturnErrorMessage(User user){
      log.info("Start UC-1 parametrized test");
      String expected = "Username is required";
      String expectedXPath = "//*[contains(text(), \""+ expected+"\")]";

      new LoginPage(this.webDriver)
            .openPage()
            .setCredentials(user) //any credentials
            .clearLoginInput()
            .clearPasswordField() //clear inputs
            .proceedLogin();

      assertThat(webDriver.findElements(By.xpath(expectedXPath))).isNotEmpty();
    }

    //UC-2 login with removed password
   @ParameterizedTest
   @MethodSource("com.github.Piotr_Duma.providers.DataProvider#getInvalidUserCredentials")
   public void loginFormWithoutPasswordShouldReturnErrorMessage(User user){
     log.info("Start UC-2 parametrized test");
     String expected = "Password is required";
     String expectedXpathLocator = "//*[contains(text(), '"+ expected+"')]";

     new LoginPage(this.webDriver)
         .openPage()
         .setCredentials(user) //any credentials
         .clearPasswordField()//clear password
         .proceedLogin();

     assertThat(webDriver.findElements(By.xpath(expectedXpathLocator))).isNotEmpty();
   }

   //UC-3 valid credentials and proceed to the dashboard page
   @ParameterizedTest
   @MethodSource("com.github.Piotr_Duma.providers.DataProvider#getValidUserCredentials")
   public void loginFormShouldReturnValidObject(User user){
     log.info("Start UC-3 parametrized test");
     String expected = "Swag Labs";
     String expectedXpathLocator = "//*[contains(text(), \""+ expected+"\")]";

     new LoginPage(this.webDriver)
         .openPage()
         .setCredentials(user)
         .proceedLogin();

     assertThat(webDriver.findElements(By.xpath(expectedXpathLocator))).isNotEmpty();
    }
}
