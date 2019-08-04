package tv.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ContentDetailsPage extends BasePage {

    @AndroidFindBy(id = "details_overview_actions")
    @iOSXCUITFindBy(id = "tvos_locator_strategy")
    private MobileElement contentDetailsActionBar;

    @AndroidFindBy(id = "details_overview_description")
    @iOSXCUITFindBy(id = "tvos_locator_strategy")
    private MobileElement contentDetailsDescription;

    public MobileElement getContentDetailsActionBar() {
        return contentDetailsActionBar;
    }

    public MobileElement getContentDetailsDescription() {
        return contentDetailsDescription;
    }

    public ContentDetailsPage(AppiumDriver driver) {
        super(driver);
    }
}
