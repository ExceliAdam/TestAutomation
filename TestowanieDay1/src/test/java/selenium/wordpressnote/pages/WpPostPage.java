package selenium.wordpressnote.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WpPostPage {
    private WebDriver driver;

    public WpPostPage(WebDriver driver, String noteURL) {
        this.driver = driver;
        this.driver.get(noteURL);
    }

    public boolean exist(String title) {
        return  driver.findElements(By.tagName("h1")).size() == 1 && driver.findElement(By.tagName("h1")).getText().equals(title);
    }
}
