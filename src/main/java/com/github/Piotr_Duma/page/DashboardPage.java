package com.github.Piotr_Duma.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractWebPage{
  private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

  public DashboardPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
    log.info("Init dashboard page");
  }

  @Override
  public AbstractWebPage openPage() {
    log.info("Process open dashboard page");
    return this;
  }
}
