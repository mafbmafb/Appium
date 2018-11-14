import Framework.Capabilities;
import Framework.DataProviders;
import Framework.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public DesiredCapabilities setCapabilities() throws FileNotFoundException {
        Gson g = new Gson();
        String pathData = "./src/test/capabilities.json";
        BufferedReader bufferReader = new BufferedReader(new FileReader(pathData));
        Capabilities capability = g.fromJson(bufferReader, Capabilities.class);

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("VERSION", capability.version);
        capabilities.setCapability("deviceName", capability.deviceName);
        capabilities.setCapability("udid", capability.udid);
        capabilities.setCapability("platformName", capability.platformName);
        capabilities.setCapability("appPackage", capability.appPackage);
        capabilities.setCapability("appActivity", capability.appActivity);
        capabilities.setCapability("autoGrantPermissions", capability.autoGrantPermissions);
        capabilities.setCapability("automationName", capability.automationName);
        capabilities.setCapability("androidInstallTimeout", capability.androidInstallTimeout);
        capabilities.setCapability("newCommandTimeout", capability.newCommandTimeout);

        return capabilities;
    }

    @Test
    public void testName(ITestContext context) throws Exception {


       //DesiredCapabilities capability = setCapabilities();

        //capabilities.setCapability("BROWSER_NAME", "Android");
         /* capabilities.setCapability("VERSION", "5.0");
        capabilities.setCapability("deviceName", "Eagle One");
        capabilities.setCapability("udid", "01234567890123456789");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.android.vending");
        capabilities.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("androidInstallTimeout", 900000);
        capabilities.setCapability("newCommandTimeout", 900000);
        */

        this.driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4729/wd/hub"), setCapabilities());


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


    @Test(groups = {"smoke", "regression"}, dataProvider = "smokeProvider", dataProviderClass = DataProviders.class)
    @Story("Fake smoke test")
    public void smokeTest(String name, int arg) throws Exception {
        System.out.println("Hello smoke");
        System.out.println(name);
        System.out.println(arg);
    }

    @Test(groups = ("regression"))
    @Story("Fake regression test")
    @Description("Fake regression test description")
    public void regressionTest() throws Exception {
        System.out.println("Regression test");

    }

    @Test
    public void loader() throws Exception {
        Gson g = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Person person1 = g.fromJson("{\"name\": \"John\"}", Person.class);
        Person person2 = gson.fromJson("{\"name\": \"John\"}", Person.class);

        System.out.println(person1.name);
        System.out.println(g.toJson(person1));
        System.out.println(gson.toJson(person2));

        String pathData = "./src/test/data.json";
        BufferedReader bufferReader = new BufferedReader(new FileReader(pathData));

        User user1 = g.fromJson(bufferReader, User.class);
        System.out.println(user1.name);
        System.out.println(user1.language);
        System.out.println(user1.location);
        System.out.println(user1.personal.age);
        System.out.println(user1.personal.hobby);


    }

    public class Person {
        public String name;
    }


}
