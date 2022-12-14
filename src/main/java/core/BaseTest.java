package core;

import enums.MobilePlatforms;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.sql.Driver;
import java.util.Objects;

public class BaseTest {
    protected BaseTest() {
    }

    @BeforeSuite(alwaysRun = true)
    protected void beforeSuite() {
        //AppiumServerManager.startAppiumServer();
    }

    @Parameters({"platformName", "udid", "deviceName", "systemPort", "chromeDriverPort", "emulator", "wdaLocalPort",
            "webkitDebugProxyPort"})
    @BeforeMethod
    protected void setUp(String platformName, String udid, String deviceName, @Optional("androidOnly") String systemPort,
                         @Optional("androidOnly") String chromeDriverPort, @Optional("androidOnly") String emulator,
                         @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort) {
        if (Objects.isNull(DriverManager.getAppiumDriver())) {
            DriverFactory.initializeDriver(MobilePlatforms.valueOf(platformName.toUpperCase()), deviceName, udid, Integer.parseInt(systemPort), emulator);
        }
    }

    protected <T extends BasePage> T getPage(Class<T> cls){

        BasePage basePage = new BasePage(DriverManager.getAppiumDriver());
        return (T) basePage.getPage(cls);

    }

    @AfterMethod
    protected void tearDown(ITestResult result) {
        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    protected void afterSuite() {
        //AppiumServerManager.stopAppiumServer();
    }
}
