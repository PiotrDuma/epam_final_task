package com.github.Piotr_Duma;

import com.github.Piotr_Duma.webdriver.SingletonWebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class WebDriverProvider {
  protected WebDriver webDriver;

  @BeforeEach
  void setUp(){
    this.webDriver = SingletonWebDriver.getWebDriver();
  }

  @AfterEach
  void shutDown(){
    SingletonWebDriver.closeDriver();
  }
}
