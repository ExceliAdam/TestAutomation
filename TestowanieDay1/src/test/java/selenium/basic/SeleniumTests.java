package selenium.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeleniumTests {
    private WebDriver driver;

    @BeforeEach
    public void startDriver(){
        //ścieżka dostępu do drivera jeśli nie dodaliśmy go w Path
        System.setProperty("webdriver.chrome.driver", "c:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver(); //To uruchamia przeglądarkę
        driver.manage().window().maximize(); //Otwieramy okno w trybie pełnoekranowym
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Podany czas to max czas jaki driver czeka, żeby element się pojawił
    }

    @Test
    public void canFindCodeSprintersOnGoogle(){


        driver.get("https://www.google.com/");
        WebElement searchBox = driver.findElement(By.name("q")); //szukamy elementu po nazwie = "q"
        searchBox.sendKeys("code sprinters"); //wpisujemy w element tekst "code sprinters"
        searchBox.submit(); //naciśnij Enter na znalezionym elemencie

        Stream<WebElement> searchResults = driver.findElements(By.cssSelector("div.rc")).stream();
        //WebElement result = searchResults
        //        .filter(r -> r.findElement(By.cssSelector("div.r > a")).getAttribute("href")
        //                .equals("http://agileszkolenia.pl/")).findFirst().get();

        Stream<WebElement> resultFiltered = searchResults
            .filter(r -> r.findElement(By.cssSelector("div.r > a")).getAttribute("href").equals("http://agileszkolenia.pl/"));
        List<WebElement> resultList = resultFiltered.collect(Collectors.toList());

        Assertions.assertEquals(1,resultList.size());
        Assertions.assertEquals("agileszkolenia.pl/", resultList.get(0).findElement(By.tagName("cite")).getText());

    }

    @Test
    public void verifyAuthorOfBlogNoteAboutTransformations(){
        driver.get("https://www.markowicz.pro/");
        //Stream elementów strony www o klasie="entry-title" przefiltrowany po tagu "a", który ma tekst="O transformacjach"
        Stream<WebElement> streamOfNotes = driver.findElements(By.className("entry-title")).stream()
                .filter(n -> n.findElement(By.tagName("a")).getText().equals("O transformacjach"));
        //zamieniamy stream na listę
        List<WebElement> filteredNotes = streamOfNotes.collect(Collectors.toList());
        //Test sprawdzający, czy faktycznie lista/stream, który znaleźliśmy ma tylko 1 element
        Assertions.assertEquals(1,filteredNotes.size(), "Only one matching note is found on main page.");

        //Znajdźmy element po CSS #eu-cookie-law input i kliknijmy go (submit)
        driver.findElement(By.cssSelector("#eu-cookie-law input")).submit();

        filteredNotes.get(0).click();

        WebElement author = driver.findElement(By.cssSelector(".author > a"));

        Assertions.assertEquals("Rafał", author.getText(), "Proper author name is displayed");
        Assertions.assertEquals("https://markowicz.pro/author/rafal-markowicz/", author.getAttribute("href")
            , "Valid author URL is displayed");

    }

    @AfterEach
    public void closeDriver(){
        driver.quit();//zamykamy przeglądarkę
    }

}
