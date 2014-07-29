package example.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class LoginPage extends PageObject {
    
    public boolean isLoggedIn() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 0, 2000);
            wait.until(presenceOfElementLocated(By.id("logout-lnk")));
            return true;
        } catch (TimeoutException e) {
            logger().trace(e.getMessage(), e);
            return false;
        }
    }
    
    public LoginPage navigateTo() {
        URL loginUrl = getClass().getResource("../../aut/login.html");
        String fixedUrl = loginUrl.toExternalForm().replaceFirst("file:/", "file://");
        getDriver().navigate().to(fixedUrl);
        return this;
    }

    public HomePage loginStandardUser() {
        navigateTo();
        getDriver().findElement(By.id("username-txt")).sendKeys("Standard-User");
        getDriver().findElement(By.id("password-txt")).sendKeys("geheim");
        getDriver().findElement(By.id("submit-btn")).click();
        return new HomePage();
    }
    
}
