package com.tricentis.demowebshop.testsuite;

import com.tricentis.demowebshop.customlisteners.CustomListeners;
import com.tricentis.demowebshop.pages.HomePage;
import com.tricentis.demowebshop.pages.LoginPage;
import com.tricentis.demowebshop.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class LoginPageTest extends BaseTest {


    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void intInt() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    // fail check xpath
    @Test
    public void userShouldNavigateToLoginPageSuccessFully() {
        loginPage.clickLoginLink();
        Assert.assertEquals(loginPage.getWelcomeLoginMessage(), "Welcome, Please Sign In!");
    }

    @Test
    public void verifyTheErrorMessageWithInValidCredentials() {
        homePage.clickLoginLink();

        loginPage.enterEmail("prime@gmail.com");
        loginPage.enterPassword("prime123");
        loginPage.clickLoginButton();
        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.";
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void verifyThatUserShouldLogInSuccessFullyWithValidCredentials() {
        homePage.clickLoginLink();
        loginPage.enterEmail("urvi123@gmail.com"); // Use valid credentials
        loginPage.enterPassword("uvi123");
        loginPage.clickLoginButton();
        String expectedLoginText = "Welcome to our store";
        String actualLoginText = loginPage.getWelcomeMessage();
        Assert.assertEquals(expectedLoginText, actualLoginText);

    }

    @Test
    public void verifyThatUserShouldLogOutSuccessFully() {
        loginPage.clickLoginLink();
        loginPage.enterEmail("urvi123@gmail.com"); // Use valid credentials
        loginPage.enterPassword("uvi123");
        loginPage.clickLoginButton();
        loginPage.clickLogoutLink();
       // Assert.assertTrue(loginPage.isLoginLinkDisplayed());

    }
}
