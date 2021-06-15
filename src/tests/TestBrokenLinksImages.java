package tests;

import main.ImageCheck;

import main.LinkCheck;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBrokenLinksImages {

    private WebDriver driver;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver","/Library/Selenium/chromedriver");
        driver = new ChromeDriver();
    }

    @Test(description = "Find broken images on a web page")
    public void checkBrokenImages(){
        String brokenUrl = "https://demoqa.com/broken";
        driver.get(brokenUrl);
        ImageCheck Broken = new ImageCheck(driver);
        Assert.assertTrue(Broken.locateImages());

    }

    // I know there is a broken link on this page. Therefore the test should fail
    // Inspection of the output from the failed test will show which link(s) are
    // broken and action can be taken to rectified.

    @Test(description = "Find a broken link on a web page")
    public void checkBrokenLinks(){
        String brokenLink="https://demoqa.com/broken";
        driver.get(brokenLink);
        LinkCheck Broken = new LinkCheck(driver);
        Assert.assertTrue(Broken.linkCheck());
    }
}
