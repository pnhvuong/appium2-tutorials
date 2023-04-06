package tutorials;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class MigrateFromv7Tov8 {

    public static void main(String[] args) {
        AppiumDriver appiumDriver;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        /* JSON Wire Protocol */
//        capabilities.setCapability("platformName", "android");
//        capabilities.setCapability("automationName", "UIAutomator2");
//        capabilities.setCapability("deviceName", "R58N11613BD");
//        capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
//        capabilities.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");


        /* W3C Protocol */
//        capabilities.setCapability("platformName", "android");
//        capabilities.setCapability("appium:automationName", "UIAutomator2");
//        capabilities.setCapability("appium:deviceName", "R58N11613BD");
//        capabilities.setCapability("appium:appPackage", "com.sec.android.app.popupcalculator");
//        capabilities.setCapability("appium:appActivity", "com.sec.android.app.popupcalculator.Calculator");

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("R58N11613BD")
                .setAppPackage("com.sec.android.app.popupcalculator")
                .setAppActivity("com.sec.android.app.popupcalculator.Calculator");

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://127.0.0.1:4723/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (appiumServer == null) {
            throw new RuntimeException("[ERR] Somehow, we couldn't construct Appium server URL");
        }

//        appiumDriver = new AndroidDriver (appiumServer,capabilities);
        appiumDriver = new AndroidDriver(appiumServer, options);
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement numberFiveElem = appiumDriver.findElement(AppiumBy.accessibilityId("5"));
        numberFiveElem.click();
        WebElement plusBtnElem = appiumDriver.findElement(AppiumBy.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_add"));
        plusBtnElem.click();
        WebElement numberNineElem = appiumDriver.findElement(AppiumBy.accessibilityId("9"));
        numberNineElem.click();
        WebElement equalBtnElem = appiumDriver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Equal\"]"));
        equalBtnElem.click();
        WebElement resultElem = appiumDriver.findElement(AppiumBy.xpath("//android.view.ViewGroup[contains(@content-desc,\"Calculation\")]"));
        System.out.println(resultElem.getText());

    }
}
