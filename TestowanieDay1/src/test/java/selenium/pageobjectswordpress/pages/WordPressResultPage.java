package selenium.pageobjectswordpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WordPressResultPage {
    private static final By SINGLE_POST = By.cssSelector("body.single-post");
    private final WebDriver driver;

    public WordPressResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSinglePost() {
        return driver.findElements(SINGLE_POST).size() == 1;
    }

    public WordPressResultPage addComment(String commentToAdd, String authorToAdd, String emailToAdd, String pageToAdd) {
        driver.findElement(By.cssSelector("#comment")).sendKeys(commentToAdd);
        driver.findElement(By.cssSelector("#author")).sendKeys(authorToAdd);
        driver.findElement(By.cssSelector("#email")).sendKeys(emailToAdd);
        driver.findElement(By.cssSelector("#url")).sendKeys(pageToAdd);
        driver.findElement(By.cssSelector("#submit")).submit();
        //Po zostawieniu komentarza strona się przeładowuje, więc
        //powinniśmy podać nowy obiekt strony
        return new WordPressResultPage(driver);//
    }
//    public boolean contains(String resultURL) {
//        Stream<WebElement> results = getResultByUrl(resultURL);
//        return results.count() > 0;
//    }
    public boolean isCommentAdd(String commentToAdd) {
        Stream<WebElement> result = findCommentsByText(commentToAdd);
        return result.collect(Collectors.toList()).size()==1;
    }

    private Stream<WebElement> findCommentsByText(String commentToAdd) {
        return driver.findElements(By.cssSelector(".comment-content")).stream()
            .filter(n -> n.findElement(By.tagName("p")).getText().equals(commentToAdd));
    }
}
