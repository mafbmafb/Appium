import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by mafb on 13.11.2018.
 */

@Epic("Google apps")
@Feature("Play store")
public class AppiumTest {

    public AndroidDriver<AndroidElement> driver;
    public FluentWait<WebDriver> wait;
    public DesiredCapabilities capabilities;


    //  @Test
    public void testName(ITestContext context) throws Exception {
        capabilities = new DesiredCapabilities();
        //capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "5.0");
        capabilities.setCapability("deviceName", "Eagle One");
        capabilities.setCapability("udid", "01234567890123456789");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.android.vending");
        capabilities.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("androidInstallTimeout", 900000);
        capabilities.setCapability("newCommandTimeout", 900000);


        this.driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4729/wd/hub"), capabilities);


        this.wait = new WebDriverWait(driver, 120)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(ClassCastException.class);
        context.setAttribute("driver", this.driver);
        context.setAttribute("wait", this.wait);

        // wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[@id='com.android.vending:id/play_card']")));
        StoreStartPage storeStartPage = new StoreStartPage(context);
        storeStartPage.isDisplayed();
        storeStartPage.searchInStore();
        Thread.sleep(1000);
        driver.quit();
    }

    @Test(groups={"smoke","regression"})
    @Story("Fake smoke test")
    public void smokeTest() throws Exception {
        System.out.println("Hello smoke");
    }

    @Test(groups=("regression"))
    @Story("Fake regression test")
    @Description("Fake regression test description")
    public void regressionTest() throws Exception {
        System.out.println("Regression test");

    }
}
