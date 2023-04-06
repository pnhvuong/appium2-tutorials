import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class AppiumLaunchTest {

    public static void main(String[] args) throws InterruptedException {
        AppiumDriver appiumDriver;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        /* JSON Wire Protocol */
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("automationName", "UIAutomator2");
        capabilities.setCapability("deviceName", "R58N11613BD");
        capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        capabilities.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");

        // Specify Appium Server URL
        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (appiumServer == null) {
            throw new RuntimeException("[ERR] Somehow, we couldn't construct Appium server URL");
        }
        appiumDriver = new AndroidDriver(appiumServer, capabilities);
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Thread.sleep(300);

        appiumDriver.quit();
    }
}
