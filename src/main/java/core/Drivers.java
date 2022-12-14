package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Drivers {
    public static AppiumDriver<MobileElement> getAndroidDriver(String deviceName, String udid, int port, String emulator) {
        try {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2); // Specific to Android
            capability.setCapability(MobileCapabilityType.UDID, udid); // To uniquely identify device
            capability.setCapability(MobileCapabilityType.APP, "C:\\Users\\mrpra\\Desktop\\Appium_Page_Factory_Framework\\apps\\android_sauce_labs_sample.apk");
            capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.swaglabsmobileapp");
            capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.swaglabsmobileapp.MainActivity");
            capability.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, port); // To set different port for each thread - This port is used to communicate with UiAutomator2
            if (emulator.equalsIgnoreCase("yes")) {
                capability.setCapability(AndroidMobileCapabilityType.AVD, deviceName);
                capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, 30);
            }
            return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capability);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AppiumDriver<MobileElement> getIOSDriver(String deviceName, String udid, int port) {
        try {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.IOS);
            capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capability.setCapability(MobileCapabilityType.UDID, udid);
            capability.setCapability(MobileCapabilityType.APP, "");
            capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "");
            capability.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, port); // To set different port for each thread - This port is used to communicate with WebDriverAgent driver

            return new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capability);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
