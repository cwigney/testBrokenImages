package main;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LinkCheck {

    private WebDriver driver;
    private By webElementLinks = By.tagName("a");

    public LinkCheck(WebDriver driver){
        this.driver = driver;
    }
    public boolean linkCheck(){
        List<WebElement> pageLinks = driver.findElements(webElementLinks);

        System.out.println("The page under test has :" + pageLinks.size() + " links");

        boolean status = true;

        for(WebElement link:pageLinks){
            if (link != null) {

                CloseableHttpClient httpClient = HttpClients.createDefault();
                String linkURL = link.getAttribute("href");
                HttpGet httpget = new HttpGet(linkURL);

                try {
                    CloseableHttpResponse response = httpClient.execute(httpget);
                    if(response.getCode() == 200){
                        System.out.println("Link is: "+linkURL+" Link passed!");
                    }else {
                        System.out.println("Link URL is: "+linkURL+" Link is broken!");
                        status = false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else{
                System.out.println("The page contains no links");
            }
        } return status;


    }
}
