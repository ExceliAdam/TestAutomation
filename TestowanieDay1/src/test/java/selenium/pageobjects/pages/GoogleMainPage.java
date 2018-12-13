package selenium.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMainPage {

    private static final String GOOGLE_PAGE_URL = "http://www.google.com";
    private static final By SEARCH_BOX_LOC = By.name("q");
    private final WebDriver driver;

    public GoogleMainPage(WebDriver driver) {
        //możemy zapisywać jako inna nazwa drivera
//        gmpDriver = driver;
//        gmpDriver.get(GOOGLE_PAGE_URL);
        //ale możemy też nazwać drivera tak samo
        this.driver = driver;
        this.driver.get(GOOGLE_PAGE_URL);
    }

    public GoogleResultPage search(String searchQeury) {
        WebElement searchBox =  driver.findElement(SEARCH_BOX_LOC);
        searchBox.sendKeys(searchQeury);
        searchBox.submit();
        return new GoogleResultPage(driver);
    }
}
