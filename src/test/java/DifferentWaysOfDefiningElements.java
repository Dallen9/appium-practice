import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pages.GraphicPage;

public class DifferentWaysOfDefiningElements {

    public static void main(String[] args)  throws Exception{
        AppiumDriver<MobileElement> driver = StartDriverSession.initializeDriver("Android");
        GraphicPage graphicPage = new GraphicPage(driver);
        graphicPage.navigateToGraphics();
//        DifferentWaysOfDefiningElements differentWaysOfDefiningElements = new DifferentWaysOfDefiningElements(driver);


    }
}
