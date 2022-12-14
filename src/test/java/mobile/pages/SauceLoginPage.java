package mobile.pages;

import core.BasePage;
import core.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;


public class SauceLoginPage extends BasePage<SauceLoginPage> {

    public SauceLoginPage(BasePage page){
        super(page);
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getAppiumDriver()), SauceLoginPage.class);
    }

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(accessibility = "test-Username")
    private MobileElement txtFieldUsername;

    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(accessibility = "test-Password")
    private MobileElement txtFieldPassword;

    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    private MobileElement btnLogin;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")
    private MobileElement errorMessage;

    public SauceLoginPage setUsername(String username) {
        txtFieldUsername.sendKeys(username);
        return this;
    }

    public SauceLoginPage setPassword(String password) {
        txtFieldPassword.sendKeys(password);
       return this;
    }

    public SauceLoginPage tapOnLogin() {
        btnLogin.click();
        return this;
    }


    public String getErrorText() {
        return errorMessage.getText();
    }
}
