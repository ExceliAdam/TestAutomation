package selenium.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class GoogleResultPage {
    private static final By RESULT_LOC = By.cssSelector("#search .rc .r");
    private final WebDriver grpDriver;

    public GoogleResultPage(WebDriver driver) {
        grpDriver = driver;
    }

    public boolean contains(String resultURL) {
        Stream<WebElement> results = getResultByUrl(resultURL);
        return results.count() > 0;
    }

    public boolean containsResultWithTitle(String pageURL, String pageTitle) {
        Stream<WebElement> results = getResultByUrl(pageURL)
                .filter(r -> r.findElement(By.tagName("h3")).getText().equals(pageTitle));
        return results.count() > 0;
    }

    private Stream<WebElement> getResultByUrl(String resultURL) {
        return grpDriver.findElements(RESULT_LOC).stream()
                .filter(r -> r.findElement(By.tagName("a")).getAttribute("href").equals(resultURL));
    }
}
