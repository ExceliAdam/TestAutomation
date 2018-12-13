package selenium.wordpressnote.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WpNotePage {
    private WebDriver driver;

    public WpNotePage(WebDriver driver) {
        this.driver = driver;
    }

    public String addNote(String title, String note) {
        //#title
        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).sendKeys(title);
        //.wp-editor-area
        driver.findElement(By.cssSelector(".wp-editor-area")).sendKeys(note);
        //#sample-permalink a
        return  driver.findElement(By.cssSelector("#sample-permalink a")).getAttribute("href");
    }

    public WpNotePage publishNote() {
        WebElement button = driver.findElement(By.cssSelector("#publish"));
        button.click();
        //Nie jestem pewien czy ten wait jest faktycznie potrzebny, ale ogólnie czeka dopóki nie zmieni się tekst
        //w 'polu' "#post-status-display" na "Opublikowano"
        WebElement postStatus = driver.findElement(By.cssSelector("#post-status-display"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(postStatus, "Opublikowano"));

        return new WpNotePage(driver);
    }

    public void logout() {

        //Tutaj sobie sprawdzam, czy mogę kliknąć w znaleziony obiekt
        //result.collect(Collectors.toList()).get(0).click();
        //Dodajemy sobie akcje rozwijania menu
        Actions rozwin = new Actions(driver);
        rozwin.moveToElement( driver.findElement(By.cssSelector(".display-name")) ).build().perform();
        // Href się zmienia
        //Stream<WebElement> logout = findLogoutByHref("https://automatyzacja.benedykt.net/wp-login.php?action=logout&amp;_wpnonce=64960cfdf7");
        Stream<WebElement> logout = findLogoutByName("Wyloguj się");
        logout.collect(Collectors.toList()).get(0).click();
        //return new WpNotePage(driver);

        //.ab-item
        //href=

        int a = 1;
    }
//    private Stream<WebElement> findLogoutByHref(String aHref) {
//        return driver.findElements(By.cssSelector(".ab-item"))
//                .stream().filter(n -> n.getAttribute("href").equals(aHref));
//        //int i = tmpStream.collect(Collectors.toList()).size();
//    }
    private Stream<WebElement> findLogoutByName(String tekst) {
        return driver.findElements(By.cssSelector(".ab-item")).stream()
                .filter(n -> n.getText().equals(tekst));
    }
}

