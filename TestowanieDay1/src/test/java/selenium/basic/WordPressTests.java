package selenium.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordPressTests {
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
    public void addComenntToBenedyktPage(){
        driver.get("https://automatyzacja.benedykt.net/");
        //h2.entry-title
        driver.findElement(By.cssSelector("h2.entry-title")).findElement(By.tagName("a")).click();

        //Asercja od prowadzącego, która sprawdza, czy otworzy się nowa strona, która ma CSS "body.single-post"
        Assertions.assertEquals(1, driver.findElements(By.cssSelector("body.single-post")).size()
            , "Single note page is displayed");

        //#comment

        //WebElement commentForm = driver.findElement(By.cssSelector("#comment"));
        //UUID - Universally Unique Identifier
        String commentToAdd = "Comment by Adam K. at: " + UUID.randomUUID();
        //timeStamp
        //String commentToAdd = "Comment by Adam K. at: " + new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(Calendar.getInstance().getTime());
        //commentForm.sendKeys(commentToAdd);
        driver.findElement(By.cssSelector("#comment")).sendKeys(commentToAdd);
        //#author
        driver.findElement(By.cssSelector("#author")).sendKeys("Excel i Adam");
        //authorForm.sendKeys("Excel i Adam");
        //#email
        WebElement emailForm = driver.findElement(By.cssSelector("#email"));
        emailForm.sendKeys("adam@exceliadam.pl");
        //#url - https://exceliadam.pl/
        WebElement wwwForm = driver.findElement(By.cssSelector("#url"));
        wwwForm.sendKeys("https://exceliadam.pl/");
        //#submit
        WebElement submitForm = driver.findElement(By.cssSelector("#submit"));
        submitForm.click();
        //.comment-content>p
        Stream<WebElement> addedComments = driver.findElements(By.cssSelector(".comment-content")).stream()
                .filter(n -> n.findElement(By.tagName("p")).getText().equals(commentToAdd));
        //zamieniamy stream na listę
        List<WebElement> filtereComments = addedComments.collect(Collectors.toList());
        //Test sprawdzający, czy faktycznie lista/stream, który znaleźliśmy ma tylko 1 element
        Assertions.assertEquals(1,filtereComments.size(), "Only one matching comment is found on page.");
    }


    @AfterEach
    public void closeDriver(){
        driver.quit();//zamykamy przeglądarkę
    }

}
