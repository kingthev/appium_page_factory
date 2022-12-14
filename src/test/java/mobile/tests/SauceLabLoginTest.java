package mobile.tests;

import core.BaseTest;
import mobile.pages.SauceLoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLabLoginTest extends BaseTest {
    SauceLoginPage sauceloginPage;

    @BeforeMethod
    public void initialize() {
        sauceloginPage = getPage(SauceLoginPage.class);
    }

    @Test
    public void invalidLogin() {
        sauceloginPage.setUsername("standard_user")
                .setPassword("secret_sauce")
                .tapOnLogin();
        String invalidLoginErrorMessage = sauceloginPage.getErrorText();

        Assert.assertEquals(invalidLoginErrorMessage, "", "Assertion for Invalid login error");
    }
}
