import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class AndroidFindElements {
    public static void main(String[] args) throws Exception {
        AppiumDriver<MobileElement> driver = StartDriverSession.initializeDriver("Android");
        MobileElement myElement =  driver.findElementByAccessibilityId("Accessibility");
        System.out.println(myElement.getText());

        myElement = driver.findElementById("android:id/text1");
        System.out.println(myElement.getText());

        myElement = driver.findElementByClassName("android.widget.TextView");
        System.out.println(myElement.getText());

        myElement = driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Accessibility\"]");
        System.out.println(myElement.getText());

        //Custom xPath to use name(text) attribute
        myElement = driver.findElementByXPath("//*[@text=\"Accessibility\"]");
        System.out.println(myElement.getText());

        System.out.println("Session ID: " + driver.getSessionId());
    }
}
