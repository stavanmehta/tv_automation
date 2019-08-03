package tv.automation.pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class BasePage {

    protected static AndroidDriver driver;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        initElements(new AppiumFieldDecorator(driver), this);
    }

    public void goBack() {
        this.driver.navigate().back();
    }

    public void clickDownArrow() {
        this.driver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
    }

    public void clickUpArrow() {
        this.driver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
    }

    private void swipeVertical(WebDriver driver, int startPoint, int endPoint) {
        int anchor = 550;
        new TouchAction(this.driver).press(PointOption.point(anchor, startPoint)).waitAction()
                .moveTo(PointOption.point(anchor, endPoint)).release().perform();
    }
}
