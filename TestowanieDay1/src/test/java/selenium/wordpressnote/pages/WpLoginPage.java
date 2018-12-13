package selenium.wordpressnote.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WpLoginPage {
    private static final String URL = "https://automatyzacja.benedykt.net/wp-admin";
    private WebDriver driver;

    public WpLoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(URL);
    }

    public WpMainPage login(String user, String pass) {
        driver.findElement(By.cssSelector("#user_login")).click();
        driver.findElement(By.cssSelector("#user_login")).sendKeys(user);
        driver.findElement(By.cssSelector("#user_pass")).sendKeys(pass);
        driver.findElement(By.cssSelector("#wp-submit")).submit();
        return new WpMainPage(driver);
    }
}
