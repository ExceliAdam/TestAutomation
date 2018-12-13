package selenium.pageobjectswordpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WordPressMainPage {

    private static final String BLOG_URL = "https://automatyzacja.benedykt.net/";
    private final WebDriver driver;

    public WordPressMainPage(WebDriver driver){
        this.driver = driver;
        this.driver.get(BLOG_URL);
    }

    public WordPressResultPage searchCSS(String cssSelector) {
        WebElement searchLink = driver.findElement(By.cssSelector(cssSelector)).findElement(By.tagName("a"));
        searchLink.click();
        return new WordPressResultPage(driver);
    }
}
