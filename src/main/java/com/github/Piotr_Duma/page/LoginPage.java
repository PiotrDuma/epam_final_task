package com.github.Piotr_Duma.page;

import com.github.Piotr_Duma.utils.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractWebPage {
  private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
  private static final String URL_VARIABLE = "page.login.url";
  private static final String LOGIN_LOCATOR = "//input[@id=\"user-name\"]";
  private static final String PASSWORD_LOCATOR = "//input[@id=\"password\"]";
  private static final String BUTTON_LOCATOR = "//input[@id=\"login-button\"]";

  @FindBy(xpath = LOGIN_LOCATOR)
  private WebElement loginField;

  @FindBy(xpath = PASSWORD_LOCATOR)
  private WebElement passwordField;

  @FindBy(xpath = BUTTON_LOCATOR)
  private WebElement loginButton;

  public LoginPage(WebDriver driver) {
    super(driver);
    log.info("Init login page");
  }

  @Override
  public LoginPage openPage() {
    log.info("Process open login page");
    String url = loadURLVariable(URL_VARIABLE);
    driver.navigate().to(url);
    return this;
  }

  public LoginPage setCredentials(User user){
    fillElementWithText(loginField, user.getLogin());
    fillElementWithText(passwordField, user.getPassword());
    return this;
  }

  public LoginPage clearLoginInput(){
    clearInputField(loginField);
    return this;
  }

  public LoginPage clearPasswordField(){
    clearInputField(passwordField);
    return this;
  }

  public AbstractWebPage proceedLogin(){
    clickElement(loginButton);
    return new DashboardPage(driver);
  }
}
