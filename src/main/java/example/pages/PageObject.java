package example.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import example.core.ExecutionConfigurator;
import example.core.MyWebDriverProvider;

/**
 * Der Zweck von PageObjects wird hier beschrieben:
 * http://code.google.com/p/selenium/wiki/PageObjects
 * Eine Superklasse für alle PageObjects ist meist sinnvoll.
 * 
 * Man könnte diese Klasse oder seine eigentlichen PageObjects von 
 * "org.jbehave.web.selenium.WebDriverPage" ableiten. Es gab allerdings 
 * einige Kompatibilitätsprobleme aufgrund der hier frei kombinierten 
 * Versionen von "jbehave-web-selenium", "selenium-java" und 
 * "jbehave-core".
 */
public class PageObject {
    
    private final Logger logger = Logger.getLogger(getClass());
    
    private final MyWebDriverProvider driverProvider = ExecutionConfigurator.getInstance().getDriverProvider();
    
    protected final MyWebDriverProvider getDriverProvider() {
        return this.driverProvider;
    }

    protected final WebDriver getDriver() {
        return getDriverProvider().get();
    }
    
    public boolean isErrorDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 0, 2000);
            wait.until(presenceOfElementLocated(By.id("error-msg")));
            return true;
        } catch (TimeoutException e) {
            logger().trace(e.getMessage(), e);
            return false;
        }
    }
    
    protected Logger logger() {
        return this.logger;
    }
    
}
