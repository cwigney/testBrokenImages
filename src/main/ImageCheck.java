package main;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class ImageCheck {

    private final WebDriver driver;
    private final By image = By.tagName("img");
    private boolean status= true;

    public ImageCheck(WebDriver driver) {
        this.driver = driver;
    }

    public boolean locateImages() {
        List<WebElement> imageList = driver.findElements(image);
        System.out.println("The page under test has :" + imageList.size() + " images");

        for (WebElement image : imageList) {

            if (image != null) {

                CloseableHttpClient httpClient = HttpClients.createDefault();
                String imageURL = image.getAttribute("src");
                HttpGet httpget = new HttpGet(imageURL);

                try {
                    CloseableHttpResponse response = httpClient.execute(httpget);
                    if(response.getCode() == 200){
                        System.out.println("Image URL is: "+imageURL+" Image passed!");
                    }else {
                        System.out.println("Image URL is: "+imageURL+" Image is broken!");
                        status = false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else{
                System.out.println("The page contains no images");
            }
        } return status;
    }
}
