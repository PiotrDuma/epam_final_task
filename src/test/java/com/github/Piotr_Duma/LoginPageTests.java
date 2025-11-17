package com.github.Piotr_Duma;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.Piotr_Duma.page.LoginPage;
import com.github.Piotr_Duma.utils.User;
import java.time.Duration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPageTests extends WebDriverProvider{
    private static final Logger log = LoggerFactory.getLogger(LoginPageTests.class);

    //UC-1 test login form with empty credentials
    @ParameterizedTest
    @MethodSource("com.github.Piotr_Duma.DataProvider#getInvalidUserCredentials")
    public void loginFormWithEmptyCredentialsShouldReturnErrorMessage(User user){
      log.info("Start UC-1 parametrized test");
      String expected = "Username is required";
      String expectedXPath = "//*[contains(text(), \""+ expected+"\")]";

      LoginPage loginPage = new LoginPage(this.webDriver)
            .openPage()
            .setCredentials(user) //any credentials
            .clearLoginInput()
            .clearPasswordField(); //clear inputs

      //explicit wait for chrome synchronization
      WebElement field = webDriver.findElement(By.id("user-name"));
      new WebDriverWait(this.webDriver, Duration.ofSeconds(5)).until(
          ExpectedConditions.attributeToBe(field, "value", "")
      );

      loginPage.proceedLogin();

//        assertThat(webDriver.getPageSource()).contains(expected); //chrome doesn't work
      assertThat(webDriver.findElements(By.xpath(expectedXPath))).isNotEmpty();
    }

    //UC-2 login with removed password
   @ParameterizedTest
   @MethodSource("com.github.Piotr_Duma.DataProvider#getInvalidUserCredentials")
   public void loginFormWithoutPasswordShouldReturnErrorMessage(User user){
     log.info("Start UC-2 parametrized test");
     String expected = "Password is required";
     String expectedXpathLocator = "//*[contains(text(), '"+ expected+"')]";

     LoginPage loginPage = new LoginPage(this.webDriver)
         .openPage()
         .setCredentials(user) //any credentials
         .clearPasswordField();//clear password

     //explicit wait for chrome synchronization
     WebElement field = webDriver.findElement(By.name("password"));
     new WebDriverWait(this.webDriver, Duration.ofSeconds(5)).until(
         ExpectedConditions.attributeToBe(field, "value", "")
     );

     loginPage.proceedLogin();

     assertThat(webDriver.findElements(By.xpath(expectedXpathLocator))).isNotEmpty();
   }

   //UC-3 valid credentials and proceed to the dashboard page
   @ParameterizedTest
   @MethodSource("com.github.Piotr_Duma.DataProvider#getValidUserCredentials")
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
