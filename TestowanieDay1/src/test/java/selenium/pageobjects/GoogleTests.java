package selenium.pageobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import selenium.pageobjects.pages.GoogleMainPage;
import selenium.pageobjects.pages.GoogleResultPage;

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
        Assertions.assertTrue(resultPage.contains(pageURL));
        Assertions.assertTrue(resultPage.containsResultWithTitle(pageURL, pageTitle)
            , "Scrum.org page has correct title" );
    }

    @Test
    public void canFindCodeSprinters(){
        String pageURL = "http://agileszkolenia.pl/";
        String pageTitle = "Code Sprinters - Agile Experts -";

        GoogleMainPage googlePage = new GoogleMainPage(driver);
        GoogleResultPage resultPage = googlePage.search("Code Sprinters");
        Assertions.assertTrue(resultPage.contains(pageURL));
        Assertions.assertTrue(resultPage.containsResultWithTitle(pageURL, pageTitle));
    }

}
