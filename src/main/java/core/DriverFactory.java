package core;

import io.appium.java_client.AppiumDriver;
import java.util.Objects;
import enums.MobilePlatforms;


public class DriverFactory {
    public static void initializeDriver(MobilePlatforms mobilePlatformName, String devicename, String udid, int port, String emulator) {
        AppiumDriver driver = null;
        switch (mobilePlatformName) {
            case ANDROID:
                driver = Drivers.getAndroidDriver(devicename, udid, port, emulator);
                break;
            case IOS:
                driver = Drivers.getIOSDriver(devicename, udid, port);
                break;
        }
        DriverManager.setAppiumDriver(driver);
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getAppiumDriver())) {
            DriverManager.getAppiumDriver().quit();
            DriverManager.unload();
        }
    }
}
