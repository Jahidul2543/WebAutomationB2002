import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class SampleTest {

    private static Logger log = LogManager.getLogger(SampleTest.class.getName());

    WebDriver  driver = null;

    @BeforeMethod
    public void setUp(){
        /**
         * This method will run before every @Test annotated method
         *
         * */
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
        // Open Browser with the Home Page
        driver.get("http://automationpractice.com/");

    }

    @Test
    public void contactUsTest(){
        /**
         *
         * 1. Open Browser
         * 2. Get the URL --> Write URL
         *     -- Validate you are in the appropriate page
         * 3. Click ContactUs button
         * 4. Select Subject Heading
         * 5. Write/Send Email Address
         * 6. Order reference
         * 7. Attach File
         * 8. Write Message
         * 9. Press Send Button
         *
         * */

        String homePageTitle = driver.getTitle();
        log.info("Page Title: " + homePageTitle);

        driver.findElement(By.linkText("Contact us")).click();

        log.info("Page Title: " + driver.getTitle());

        driver.findElement(By.id("message")).sendKeys("Helllo I am writing using Java-Selenium!!!!");
        driver.findElement(By.id("submitMessage")).click();
        String actualText = driver.findElement(By.xpath("//div[@id=\"center_column\"]/div/p")).getText();
        log.info("Actual Text: " + actualText);
        String expectedText = "There is 1 error";

        Assert.assertEquals(actualText,expectedText);
        log.info("Test Passed");

    }

    @Test
    public void signInTest(){
        log.info("This is SignIn Test Case");
    }

    @AfterMethod
    public void cleanUp(){
        /**
         *
         * This method will run after every @Test annotated method
         *
         * */
        driver.quit();
    }
}
