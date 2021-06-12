package tests;

import main.ImageCheck;

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
}
