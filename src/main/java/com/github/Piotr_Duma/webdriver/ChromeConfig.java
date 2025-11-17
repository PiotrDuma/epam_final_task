package com.github.Piotr_Duma.webdriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfig {

  /**
   * Required chrome config setup to avoid chrome password popups.
   * @return chrome options setup required for driver constructor
   */
  public static ChromeOptions setUp(){
    ChromeOptions options = new ChromeOptions();

    Map<String, Object> prefs = new HashMap<>();
    prefs.put("credentials_enable_service", false);
    prefs.put("profile.autofill_profile_enabled", false);
    prefs.put("profile.autofill_credit_card_enabled", false);
    prefs.put("profile.password_manager_enabled", false);
    prefs.put("profile.password_manager_leak_detection", false);

    options.setExperimentalOption("prefs", prefs);
    options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

    options.addArguments("--disable-features=PasswordManager,PasswordManagerOnboarding");
    options.addArguments("--disable-blink-features=AutomationControlled");
    options.addArguments("--disable-single-click-autofill"); //disable autofill fields
    options.addArguments("--disable-blink-features=AutomationControlled");
    options.addArguments("--disable-save-password-bubble");
    return options;
  }
}
