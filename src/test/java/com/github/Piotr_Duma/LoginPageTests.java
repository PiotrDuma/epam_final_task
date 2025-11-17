package com.github.Piotr_Duma;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.Piotr_Duma.page.AbstractWebPage;
import com.github.Piotr_Duma.page.LoginPage;
import com.github.Piotr_Duma.utils.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPageTests extends WebDriverProvider{
    private static final Logger log = LoggerFactory.getLogger(LoginPageTests.class);
    protected static final String REPOSITORY_DESCRIPTION = "auto-generated test repo";


    //UC-1 test login form with empty credentials
    @ParameterizedTest
    @MethodSource("com.github.Piotr_Duma.DataProvider#getInvalidUserCredentials")
    public void loginFormWithEmptyCredentialsShouldReturnErrorMessage(User user){
      String expected = "Username is required";

        new LoginPage(this.webDriver)
            .openPage()
            .setCredentials(user) //any credentials
//            .setLoginFieldText("")
//            .setPasswordFieldText("") //clear inputs
            .clearLoginInput()
            .clearPasswordField()
            .proceedLogin();


//        assertThat(webDriver.getPageSource()).contains(expected);
      assertThat(webDriver.findElements(By.xpath("//*[contains(text(), \""+ expected+"\")]")));
    }
}
