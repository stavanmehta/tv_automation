package tv.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.PageFactory.initElements;

/** Base Page for all page objects, which can have common methods used across all pages.
 * Need to implement AndroidBasePage and iOSBasePage and use factory design pattern to identify OS specific BasePage
 * at runtime, and avoid maintaining mu
 * */
public abstract class BasePage {

    protected static AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        initElements(new AppiumFieldDecorator(driver), this);
    }

    public void goBack() {
        this.driver.navigate().back();
    }

    public void clickDownArrow() {
        ((AndroidDriver)this.driver).pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
    }

    public void clickUpArrow() {
        ((AndroidDriver)this.driver).pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
    }

    private void swipeVertical(WebDriver driver, int startPoint, int endPoint) {
        int anchor = 550;
        new TouchAction(this.driver).press(PointOption.point(anchor, startPoint)).waitAction()
                .moveTo(PointOption.point(anchor, endPoint)).release().perform();
    }
}
