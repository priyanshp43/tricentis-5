package com.tricentis.demowebshop.testsuite;

import com.tricentis.demowebshop.pages.*;
import com.tricentis.demowebshop.testbase.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ComputerPageTest extends BaseTest {
    HomePage homePage;
    ComputerPage computerPage;
    DesktopsPage desktopsPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;

    @BeforeMethod
    public void intInt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopsPage = new DesktopsPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyUserShouldNavigateToComputerPageSuccessfully() {
        homePage.clickOnComputersTab();
        String actualText = computerPage.getComputersText();
        String expectedText = "Computers";
        Assert.assertEquals(actualText, expectedText, "User is not navigated to Computer page");
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        homePage.clickOnComputersTab();
        computerPage.clickDesktopsLink();
        String actualText = desktopsPage.getDesktopsText();
        String expectedText = "Desktops";
        Assert.assertEquals(actualText, expectedText, "User is not navigated to Desktops page");
    }

    @Test(dataProvider = "buildYourOwnComputerData",groups = {"sanity", "regression"})
    public void verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(
            String processor, String ram, String hdd, String os, String software) {

        homePage.clickOnComputersTab();
        computerPage.clickDesktopsLink();
        desktopsPage.clickOnElement(By.linkText("Build your own computer"));
        buildYourOwnComputerPage.selectProcessor(processor);
        buildYourOwnComputerPage.selectRam(ram);
        buildYourOwnComputerPage.selectHdd(hdd);
        buildYourOwnComputerPage.selectOs(os);
        buildYourOwnComputerPage.selectSoftware(software);

        buildYourOwnComputerPage.clickAddToCartButton();
        String actualMessage = buildYourOwnComputerPage.getCartSuccessMessage();
        String expectedMessage = "The product has been added to your shopping cart";
        Assert.assertEquals(actualMessage, expectedMessage, "Product is not added to the cart successfully");
    }

    @DataProvider(name = "buildYourOwnComputerData")
    public Object[][] getBuildYourOwnComputerData() {
        return new Object[][]{
                {"2.2 GHz Intel Pentium Dual-Core E2200", "2 GB", "320 GB", "Windows 7 [+50.00]", "Microsoft Office [+$50.00]"},
                {"2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]", "4GB [+$20.00]", "400 GB [+$100.00]", "Windows 10 [+60.00]", "Acrobat Reader [+$10.00]"},
                {"2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]", "8GB [+$60.00]", "320 GB", "Ubuntu", "Total Commander [+$5.00]"}
        };
    }
}
