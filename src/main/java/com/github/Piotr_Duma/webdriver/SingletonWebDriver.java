package com.github.Piotr_Duma.webdriver;

import com.github.Piotr_Duma.utils.ApplicationPropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadLocal implementation is required for singleton WebDriver parallel testing.
 */
public class SingletonWebDriver {
  private static final Logger logger = LoggerFactory.getLogger(SingletonWebDriver.class);
  private static final String LOG_INFO = "Init WebDriver: %s";
  private static final String SYSTEM_BROWSER_PROPERTY = "browser";
  private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

  private SingletonWebDriver(){}

  public static WebDriver getWebDriver(){
    if (webDriver.get() == null){
      switch (ApplicationPropertyReader.getProperty(SYSTEM_BROWSER_PROPERTY)) {
        case "firefox" -> {
          logger.info(String.format(LOG_INFO, "Firefox WebDriver"));
          WebDriverManager.firefoxdriver().setup();
          webDriver.set(new FirefoxDriver());
        }
        case "chrome" -> {
          logger.info(String.format(LOG_INFO, "Chrome WebDriver"));
          WebDriverManager.chromedriver().setup();
          webDriver.set(ThreadGuard.protect(new ChromeDriver(ChromeConfig.setUp())));
        }
        default -> {
          logger.info(String.format(LOG_INFO, "DEFAULT DRIVER: Firefox WebDriver"));
          WebDriverManager.firefoxdriver().setup();
          webDriver.set(new FirefoxDriver());
        }
      }
      webDriver.get().manage().window().maximize();
    }
    return webDriver.get();
  }

  public static void closeDriver(){
    if(webDriver.get() != null){
      logger.info("Terminate WebDriver");
      webDriver.get().quit();
      webDriver.remove();
    }
  }
}
