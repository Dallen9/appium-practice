package runner;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestRunner {
    public AndroidDriver<MobileElement> driver;
    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //Option to use MobileCapability class instead of typing capability name manually
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability("avd", "Pixel_3a_API_30_x86");
        capabilities.setCapability("avdLaunchTimeout", 100000);
        capabilities.setCapability("newCommandTimeout", 300);
        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(url, capabilities);
        System.out.println("Session ID: " + driver.getSessionId());
    }
    @AfterClass
    public void tearDown() {
        try {
            TimeUnit.SECONDS.sleep(5);
            driver.quit();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    @Test
    public void verifyGoogleSearch() throws InterruptedException{
        String search = "Katherine Flores linkedin";
        driver.get("https://google.com");
        MobileElement input = driver.findElement(By.xpath("//input[@name=\"q\"]"));
        input.sendKeys(search);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        //When using generic AppiumDriver, cast android driver
//        ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
