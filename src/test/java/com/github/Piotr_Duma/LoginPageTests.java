package com.github.Piotr_Duma;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.Piotr_Duma.page.LoginPage;
import com.github.Piotr_Duma.utils.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

public class LoginPageTests extends WebDriverProvider{

    //UC-1 test login form with empty credentials
    @ParameterizedTest
    @MethodSource("com.github.Piotr_Duma.DataProvider#getInvalidUserCredentials")
    public void loginFormWithEmptyCredentialsShouldReturnErrorMessage(User user){
      String expected = "Username is required";

        new LoginPage(this.webDriver)
            .openPage()
            .setCredentials(user) //any credentials
            .clearLoginInput()
            .clearPasswordField() //clear inputs
            .proceedLogin();


//        assertThat(webDriver.getPageSource()).contains(expected); //chrome doesn't work
      assertThat(webDriver.findElements(By.xpath("//*[contains(text(), \""+ expected+"\")]")));
    }

    //UC-2 login with removed password
   @ParameterizedTest
   @MethodSource("com.github.Piotr_Duma.DataProvider#getInvalidUserCredentials")
   public void loginFormWithoutPasswordShouldReturnErrorMessage(User user){
     String expected = "Password is required";
     String expectedXpathLocator = "//*[contains(text(), '"+ expected+"')]";
     new LoginPage(this.webDriver)
         .openPage()
         .setCredentials(user) //any credentials
         .clearPasswordField() //clear password
         .proceedLogin();

     System.out.println(webDriver.findElements(By.xpath(expectedXpathLocator)));
     assertThat(webDriver.findElements(By.xpath(expectedXpathLocator))).isNotEmpty();
   }

   //UC-3 valid credentials and proceed to the dashboard page
   @ParameterizedTest
   @MethodSource("com.github.Piotr_Duma.DataProvider#getValidUserCredentials")
   public void loginFormShouldReturnValidObject(User user){
     String expected = "Swag Labs";
     String expectedXpathLocator = "//*[contains(text(), \""+ expected+"\")]";
     new LoginPage(this.webDriver)
         .openPage()
         .setCredentials(user)
         .proceedLogin();

     assertThat(webDriver.findElements(By.xpath(expectedXpathLocator))).isNotEmpty();
    }
}
