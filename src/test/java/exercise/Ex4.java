package exercise;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Ex4 {


    private AppiumDriver driver;

    private String searchWord = "JAVA";

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", "/Users/vitaly/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testForExercise4() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia'  input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchWord,
                "Cannot find search input",
                5
        );

        waitForResultListTitleOfSearchAndCheckTextInIt(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "Result List Of Search is not present",
                5
        );



    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private List waitForElementsIsPresent(By by, String error_message, long timeoutInSeconds) {

        waitForElementIsPresent(by, error_message, timeoutInSeconds);

        return driver.findElements(by);
    }

    private void waitForElementIsPresent(By by, String error_message, long timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private void waitForResultListTitleOfSearchAndCheckTextInIt(By by, String error_message, long timeoutInSeconds) {

        List<WebElement> webElements = waitForElementsIsPresent(by, error_message, timeoutInSeconds);


        for (WebElement element : webElements){
            Assert.assertTrue(
                    "Result of search don't contain search word = " + searchWord,
                    element.getText().toLowerCase().contains(searchWord.toLowerCase())
            );
        }
        Assert.assertTrue(
                "Quantity of ResultSearchList is less than 2",
                webElements.size() > 1
        );

    }

}
