package example.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class HomePage extends PageObject {

    public boolean isHomePage() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 0, 2000);
            wait.until(presenceOfElementLocated(By.id("home-page")));
            return true;
        } catch (TimeoutException e) {
            logger().trace(e.getMessage(), e);
            return false;
        }
    }
    
    public HomePage navigateTo() {
        URL homeUrl = getClass().getResource("../../aut/home.html");
        String fixedUrl = homeUrl.toExternalForm().replaceFirst("file:/", "file://");
        getDriver().navigate().to(fixedUrl);
        return this;
    }

    public AddressDataPage changeAddress() {
        getDriver().findElement(By.id("addressdata-lnk")).click();
        return new AddressDataPage();
    }

    public AccountDataPage changeAccount() {
        getDriver().findElement(By.id("accountdata-lnk")).click();
        return new AccountDataPage();
    }

}
