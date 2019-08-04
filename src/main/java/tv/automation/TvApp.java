package tv.automation;

import io.appium.java_client.AppiumDriver;
import tv.automation.pages.ContentDetailsPage;
import tv.automation.pages.TvAppPage;

public class TvApp {

    private AppiumDriver driver;

    public TvApp(AppiumDriver driver) {
        this.driver = driver;
    }

    public TvAppPage tvAppPage() {
        return new TvAppPage(driver);
    }

    public ContentDetailsPage contentDetailsPage() {
        return new ContentDetailsPage(driver);
    }
}
