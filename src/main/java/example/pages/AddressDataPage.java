package example.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class AddressDataPage extends PageObject {
    
    public boolean isAddressDataPage() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 0, 2000);
            wait.until(presenceOfElementLocated(By.id("addressdata-page")));
            return true;
        } catch (TimeoutException e) {
            logger().trace(e.getMessage(), e);
            return false;
        }
    }
    
}
