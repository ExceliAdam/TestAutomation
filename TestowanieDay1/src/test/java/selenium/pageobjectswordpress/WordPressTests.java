package selenium.pageobjectswordpress;

import org.junit.jupiter.api.Test;
import selenium.pageobjectswordpress.pages.WordPressMainPage;
import selenium.pageobjectswordpress.pages.WordPressResultPage;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class WordPressTests extends BaseTest{

    @Test
    public void addComenntToBenedyktPage(){
        //Open page on witch we want too leave a comment
        //driver.get("https://automatyzacja.benedykt.net/");
        WordPressMainPage wordpressPage = new WordPressMainPage(driver);
        // Klikam pierwszy link
        //driver.findElement(By.cssSelector("h2.entry-title")).findElement(By.tagName("a")).click();
        WordPressResultPage resultPage = wordpressPage.searchCSS("h2.entry-title");

        assertTrue(resultPage.isSinglePost());
        //Tworzę zmienne do komentarza
        String commentToAdd = "Comment: " + UUID.randomUUID();
        String authorToAdd = "Author: " + UUID.randomUUID();
        //Zostawiam komentarz
        WordPressResultPage refreshedResultPage = resultPage.addComment(commentToAdd, authorToAdd, "fake@gmail.com", "www.fake.pl");

        //Szukam komentarza o  autorze
        assertTrue(refreshedResultPage.isCommentAdd(commentToAdd));
    }
}
