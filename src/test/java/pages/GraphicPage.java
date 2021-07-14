package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class GraphicPage {
    public GraphicPage() {
    }
    public GraphicPage(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Graphics\"]")
    private MobileElement graphics;

    public void navigateToGraphics() {
        graphics.click();
    }
}
