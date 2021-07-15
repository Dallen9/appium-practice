package runner;

 import io.appium.java_client.AppiumDriver;
 import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
 import org.openqa.selenium.remote.BrowserType;
 import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestRunner {
    public AppiumDriver<?> driver;
    @BeforeTest
    public void setup() throws MalformedURLException {
        String browserPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
             File.separator + "resources" +  File.separator + "chromedriver";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("newCommandTimeout", 300);
        //Option to use MobileCapability class instead of typing capability name manually
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
        capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
        capabilities.setCapability("chromedriverExecutable", browserPath);
        capabilities.setCapability("newCommandTimeout", 300);
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
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
    public void verifyGoogleSearch() {
        String search = "cat";
        driver.get("https://google.com");
        WebElement input = driver.findElement(By.xpath("//input[@name=\"q\"]"));
        input.sendKeys(search);
//        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        //When using generic AppiumDriver, cast android driver
//        ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
