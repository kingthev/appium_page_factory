package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.Objects;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver<MobileElement>> threadLocalDriver =new ThreadLocal<>();

    public static AppiumDriver<MobileElement> getAppiumDriver(){
        return threadLocalDriver.get();
    }
    public static void setAppiumDriver(AppiumDriver<MobileElement> driver) {
        if(Objects.nonNull(driver)){
            threadLocalDriver.set(driver);
        }
    }
    public static void unload(){
        threadLocalDriver.remove();
    }
}
