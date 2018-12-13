package selenium.wordpressnote.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WpMainPage {
    private WebDriver driver;

    public WpMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WpNotePage clickSubMenu(String menuName) {
        Stream<WebElement> result = findMenuByName(menuName);
        //Tutaj sobie sprawdzam, czy mogę kliknąć w znaleziony obiekt
        //result.collect(Collectors.toList()).get(0).click();
        //Dodajemy sobie akcje rozwijania menu
        Actions rozwin = new Actions(driver);
        rozwin.moveToElement(result.collect(Collectors.toList()).get(0)).build().perform();
        //mimo, że w inspekcji/zbadaj było tylko "post-new.php" to trzeba podać całą ścieżkę
        Stream<WebElement> subresult = findSubMenuByAHref("https://automatyzacja.benedykt.net/wp-admin/post-new.php");
        subresult.collect(Collectors.toList()).get(0).click();
        return new WpNotePage(driver);
        //.wp-submenu.wp-submenu-wrap a   href="post-new.php"
        //return result.collect(Collectors.toList()).size()==1;
    }

    private Stream<WebElement> findSubMenuByAHref(String aHref) {
        return driver.findElements(By.cssSelector(".wp-submenu.wp-submenu-wrap a"))
                .stream().filter(n -> n.getAttribute("href").equals(aHref));
        //int i = tmpStream.collect(Collectors.toList()).size();
    }

    private Stream<WebElement> findMenuByName(String menuName) {
        return driver.findElements(By.cssSelector(".wp-menu-name")).stream()
                .filter(n -> n.getText().equals(menuName));
    }
}
