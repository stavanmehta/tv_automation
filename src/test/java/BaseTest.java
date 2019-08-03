import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import tv.automation.utils.TestTemplate;

import java.io.File;
import java.io.IOException;

public abstract class BaseTest implements TestTemplate {
    private static AppiumDriverLocalService service;
    protected AndroidDriver driver;

    @BeforeSuite
    public void globalSetup() throws IOException {

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
    }

    @AfterClass
    public void globalTearDown() {
        driver.removeApp("kentico.kentico_android_tv_app");
        driver.closeApp();
    }
}
