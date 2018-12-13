package selenium.pageobjectswordpress.pages;

import org.openqa.selenium.WebDriver;

public class WordPressMainPage {

    public static final String BLOG_URL = "https://automatyzacja.benedykt.net/";
    private final WebDriver wpDriver;

    public WordPressMainPage(WebDriver driver){
        wpDriver = driver;
        wpDriver.get(BLOG_URL);
    }

}
