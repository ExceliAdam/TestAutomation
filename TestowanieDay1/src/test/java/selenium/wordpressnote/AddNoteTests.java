package selenium.wordpressnote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import selenium.wordpressnote.pages.WpLoginPage;
import selenium.wordpressnote.pages.WpMainPage;
import selenium.wordpressnote.pages.WpNotePage;
import selenium.wordpressnote.pages.WpPostPage;

import java.util.UUID;

public class AddNoteTests extends BaseTest {

    @Test
    public void AddNote (){

        //otwieramy stronę logowania do wordpressa https://automatyzacja.benedykt.net/wp-admin
        WpLoginPage loginPage = new WpLoginPage(driver);
        //wpisujemy dane do logowania i się logujemy, czyli wchodzimy na główną stronę edycji bloga wordpressa
        String user = "automatyzacja";
        String pass = "jesien2018";
        WpMainPage mainWpPage = loginPage.login(user, pass);
        //przechodzimy na stronę dodawania nowej notatki
        //odnajdujemy menu Wpisy .wp-menu-name
        String menuName = "Wpisy";
        //Assertions.assertTrue(mainWpPage.clickSubMenu(menuName));
        WpNotePage notePage = mainWpPage.clickSubMenu(menuName);
        //dodajemy notatkę
        String title = GenerateRandomText();
        String note = "Random tekst: " + GenerateRandomText() + GenerateRandomText();
        String noteURL = notePage.addNote(title, note);
        //#publish
        WpNotePage refreshNotePage = notePage.publishNote();
        refreshNotePage.logout();
        WpPostPage postPage = new WpPostPage(driver, noteURL);

        Assertions.assertTrue(postPage.exist(title));

//        wpNewNotePage.addNote(title, note);
        //może warto sprawdzić czy notatka się dodała
        int i = 0;

    }

    private String GenerateRandomText() {
        return UUID.randomUUID().toString();
    }

}
