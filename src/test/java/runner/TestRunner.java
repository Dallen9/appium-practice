package runner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestRunner {
    public AppiumDriver<MobileElement> driver;
    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        //Option to use MobileCapability class instead of typing capability name manually
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setVersion("11");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("avd", "Pixel_3a_API_30_x86");
        capabilities.setCapability("avdLaunchTimeout", 100000);
        capabilities.setCapability("newCommandTimeout", 300);
        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(url, capabilities);
        System.out.println("Session ID: " + driver.getSessionId());
    }

    @Test
    public void verifyGoogleSearch() throws InterruptedException{
        String search = "Katherine Flores Linkedin";
        driver.get("https://google.com");
        Actions action = new Actions(driver);
        MobileElement element = driver.findElementByXPath("//input[@name=\"q\"]");
        MobileElement execute = driver.findElementByXPath("//form[@name=\"gs\"]");
        action.moveToElement(element).click().perform();
        element.sendKeys(search);
        action.moveToElement(execute).click().perform();
    }
}
