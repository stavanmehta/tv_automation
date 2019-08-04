import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import tv.automation.TvApp;

import java.io.File;
import java.io.IOException;

import static tv.automation.utils.Environments.ANDROID_TV;
import static tv.automation.utils.Environments.TV_OS;

public abstract class BaseTest {
    private static AppiumDriverLocalService service;
    private AppiumDriver driver;
    protected TvApp tvApp;

    @BeforeSuite
    public void globalSetup() throws IOException {

        String environmnet = System.getProperty("environment", "androidtv");

        if (environmnet.equalsIgnoreCase(ANDROID_TV)) {
            File classpathRoot = new File(System.getProperty("user.dir"));
            File appDir = new File(classpathRoot, "src/test/resources/apps");
            File app = new File(appDir.getCanonicalPath(), "tvapp-debug.apk");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, "UiAutomator2");

            service = AppiumDriverLocalService.buildDefaultService();
            service.start();

            driver = new AndroidDriver<WebElement>(service.getUrl(), capabilities);

        }else if (environmnet.equalsIgnoreCase(TV_OS)) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "tvOS");
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
            capabilities.setCapability("useNewWDA", false);
            capabilities.setCapability("xcodeOrgId", "[YOUR_TEAM_ID]");
            capabilities.setCapability("xcodeSigningId", "iPhone Developer");
            capabilities.setCapability(MobileCapabilityType.UDID, "[YOUR_DEVICE_UDID]");
            capabilities.setCapability(MobileCapabilityType.APP, "[PATH_TO_YOUR_IPA_FILE]");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "[YOUR_DEVICE_NAME]");

            service = AppiumDriverLocalService.buildDefaultService();
            service.start();

            driver = new IOSDriver(service.getUrl(), capabilities);
        }

        tvApp = new TvApp(driver);
    }

    @AfterSuite
    public void globalTearDown() {
        driver.removeApp("kentico.kentico_android_tv_app");
        driver.closeApp();
    }
}
