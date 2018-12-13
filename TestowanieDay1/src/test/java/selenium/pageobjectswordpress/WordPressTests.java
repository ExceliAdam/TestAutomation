package selenium.pageobjectswordpress;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pageobjects.pages.GoogleMainPage;
import selenium.pageobjectswordpress.pages.WordPressMainPage;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WordPressTests extends BaseTest{

    @Test
    public void addComenntToBenedyktPage(){
        //Open page on witch we want too leave a comment
        //driver.get("https://automatyzacja.benedykt.net/");
        WordPressMainPage wordpressPage = new WordPressMainPage(driver);
        // Klikam pierwszy link
        driver.findElement(By.cssSelector("h2.entry-title")).findElement(By.tagName("a")).click();


        Assertions.assertEquals(1, driver.findElements(By.cssSelector("body.single-post")).size()
                , "Single note page is displayed");
        //Zostawiam komentarz
        String commentToAdd = "Comment: " + UUID.randomUUID();
        String authorToAdd = "Author: " + UUID.randomUUID();

        driver.findElement(By.cssSelector("#comment")).sendKeys(commentToAdd);
        driver.findElement(By.cssSelector("#author")).sendKeys(authorToAdd);
        driver.findElement(By.cssSelector("#email")).sendKeys("fake@gmail.com");
        driver.findElement(By.cssSelector("#url")).sendKeys("www.fake.pl");
        driver.findElement(By.cssSelector("#submit")).submit();

        //Szukam komentarza o  autorze
        Stream<WebElement> addedComments = driver.findElements(By.cssSelector(".comment-content")).stream()
                .filter(n -> n.findElement(By.tagName("p")).getText().equals(commentToAdd));
        List<WebElement> filtereComments = addedComments.collect(Collectors.toList());


        Assertions.assertEquals(1,filtereComments.size(), "Only one matching comment is found on page.");


    }
}
