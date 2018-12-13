package selenium.pageobjects;


import org.junit.jupiter.api.Test;
import selenium.pageobjects.pages.GoogleMainPage;
import selenium.pageobjects.pages.GoogleResultPage;
//Dopisujemy .* i static, żebyśmy mogli korzystać z metod statycznych,
//czyli nie musimy przed nimi dopisywać Assertions,
//zaproponuje to sam, gdy pobieramy dużo (przynajmniej 3) metody z tego importu
//import org.junit.jupiter.api.Assertions;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleTests extends BaseTest {

    @Test
    public void canFindScrumOrgGoogle(){
        String pageURL = "https://www.scrum.org/";
        String pageTitle = "Scrum.org: Homepage";
        //Open Google Main Page
        GoogleMainPage googlePage = new GoogleMainPage(driver);

        //Search For Scrum.org
        GoogleResultPage resultPage = googlePage.search("Scrum.org");

        //Assert Srum.org page is found
        assertTrue(resultPage.contains(pageURL));
        assertTrue(resultPage.containsResultWithTitle(pageURL, pageTitle)
            , "Scrum.org page has correct title" );
    }

    @Test
    public void canFindCodeSprinters(){
        String pageURL = "http://agileszkolenia.pl/";
        String pageTitle = "Code Sprinters - Agile Experts -";

        GoogleMainPage googlePage = new GoogleMainPage(driver);
        GoogleResultPage resultPage = googlePage.search("Code Sprinters");
        assertTrue(resultPage.contains(pageURL));
        assertTrue(resultPage.containsResultWithTitle(pageURL, pageTitle));

        String random = generateRandomText();
    }

}
