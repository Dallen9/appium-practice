import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class StartDriverSession {

    public static AppiumDriver<MobileElement>  initializeDriver(String platformName) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Option to use MobileCapability class instead of typing capability name manually
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,  platformName);
        //  How long the app will stay open while idle
        capabilities.setCapability("newCommandTimeout", 300);
        URL url = new URL("http://localhost:4723/wd/hub");

        switch(platformName) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
                capabilities.setCapability("avd", "Pixel_3a_API_30_x86");
                capabilities.setCapability("avdLaunchTimeout", 100000);
                capabilities.setCapability("appPackage", "io.appium.android.apis");
                capabilities.setCapability("appActivity", "ApiDemos");
                String andAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                        File.separator + "resources" +  File.separator + "ApiDemos-debug.apk";
//                capabilities.setCapability(MobileCapabilityType.APP, andAppUrl);
                return new AndroidDriver<>(url, capabilities);
            case "iOS":
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setVersion("14");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
                capabilities.setCapability(MobileCapabilityType.UDID, "848584959N5-54IOU5UB-495H5-0444");
                capabilities.setCapability("simulatorStartupTimeout", 180000);
                String iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                        File.separator + "resources" +  File.separator + "UIKitCatalog-iphonesimulator.app";
                capabilities.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
//                capabilities.setCapability("app", iOSAppUrl);
                return new IOSDriver<>(url, capabilities);
            default:
                throw new Exception("Invalid platform");
        }
    }
}
