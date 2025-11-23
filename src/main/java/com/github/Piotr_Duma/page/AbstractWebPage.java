package com.github.Piotr_Duma.page;

import com.github.Piotr_Duma.utils.ApplicationPropertyReader;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractWebPage {
  protected static final int WAIT_TIMEOUT_SECONDS = 10;
  protected WebDriver driver;
  protected WebDriverWait wait;

  protected AbstractWebPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    PageFactory.initElements(driver, this);
  }

  public abstract AbstractWebPage openPage();

  protected void clickElement(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
    element.click();
  }

  protected void fillElementWithText(WebElement element, String text) {
    wait.until(ExpectedConditions.visibilityOf(element));
    element.clear();
    element.sendKeys(text);
  }

  /**
   * Method always returns a String variable, otherwise it throws a runtime exception.
   * @return non null String variable.
   */
  protected String loadURLVariable(String urlVariableName) {
    return ApplicationPropertyReader.getProperty(urlVariableName);
  }
}
