/**
 * Created by mafb on 13.11.2018.
 */

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.net.URL;


public class StoreStartPage {
    public AndroidDriver<AndroidElement> driver;
    public FluentWait<WebDriver> wait;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='GRY']")
    private MobileElement gameButton;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Wyszukaj']")
    private MobileElement searchField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Szukaj w Google Play']")
    private MobileElement searchFieldVisible;

    public StoreStartPage(ITestContext context) {
        this.driver = (AndroidDriver<AndroidElement>)context.getAttribute("driver");
        this.wait = (FluentWait<WebDriver>)context.getAttribute("wait");

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(gameButton));
        return true;
    }

    public void searchInStore() {
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.click();
        wait.until(ExpectedConditions.elementToBeClickable(searchFieldVisible));
        searchFieldVisible.sendKeys("Facebook");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
