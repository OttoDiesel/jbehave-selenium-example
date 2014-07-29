package example.core;

import static org.jbehave.web.selenium.PropertyWebDriverProvider.Browser.CHROME;
import static org.jbehave.web.selenium.PropertyWebDriverProvider.Browser.FIREFOX;
import static org.jbehave.web.selenium.PropertyWebDriverProvider.Browser.HTMLUNIT;
import static org.jbehave.web.selenium.PropertyWebDriverProvider.Browser.IE;

import java.awt.Toolkit;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jbehave.web.selenium.DelegatingWebDriverProvider;
import org.jbehave.web.selenium.PropertyWebDriverProvider.Browser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class MyWebDriverProvider extends DelegatingWebDriverProvider {

    private static final Logger LOGGER = Logger.getLogger(MyWebDriverProvider.class);
    
    @Override
    public void initialize() {
        // FIXME Der zu verwendende Browser sollte nicht hartcodiert sein.
        Browser browser = Browser.FIREFOX;
        WebDriver driver = createDriver(browser);
        super.delegate.set(driver);
        adjustWindow(driver);
    }

    @Override
    public void end() {
        super.delegate.get().quit();
        super.delegate.set(null);
    }

    private WebDriver createDriver(Browser browser) {
        WebDriver driver;
        LOGGER.info(String.format("Starting browser '%s' ...", browser));
        if (CHROME == browser) {
            driver = createChromeDriver();
        } else if (FIREFOX == browser) {
            driver = createFirefoxDriver();
        } else if (HTMLUNIT == browser) {
            driver = createHtmlUnitDriver();
        } else if (IE == browser) {
            driver = createInternetExplorerDriver();
        } else {
            LOGGER.warn(String.format("Using HtmlUnitDriver as browser since passed type '%s' did not match any value of '%s'...", browser,
                    ArrayUtils.toString(Browser.values())));
            driver = createHtmlUnitDriver();
        }
        return driver;
    }

    protected WebDriver createChromeDriver() {
        return WebDriverSupplier.createChromeDriver(new ChromeOptions());
    }

    protected WebDriver createFirefoxDriver() {
        return WebDriverSupplier.createFirefoxDriver(new FirefoxProfile(), DesiredCapabilities.firefox());
    }

    protected WebDriver createHtmlUnitDriver() {
        return WebDriverSupplier.createHtmlUnitDriver();
    }

    protected WebDriver createInternetExplorerDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        // Ist je nach Security-Einstellungen manchmal notwendig:
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        // Fix für Fokus-Verhalten:
        capabilities.setCapability("nativeEvents", false);
        return WebDriverSupplier.createInternetExplorerDriver(capabilities);
    }

    /**
     * Damit im HTML-Report ein Link zum Screenshot erscheint, 
     * wird die Methode in der Superklasse überschrieben.
     */
    @Override
    public boolean saveScreenshotTo(String path) {
        return saveScreenshotTo(path, Level.ERROR);
    }

    public boolean saveScreenshotTo(String path, Level logLevel) {
        boolean result = false;
        String relativePath = path.replaceFirst(".*(\\\\|/)target(\\\\|/)jbehave", "..");
        String message = String.format("<a href=\"%s\">Screenshot saved to: %s</a>", relativePath, relativePath);
        LOGGER.log(logLevel, message);
        result = super.saveScreenshotTo(path);
        return result;
    }

    /**
     * Position und Grösse des Browserfensters sollten anfangs initialisiert werden.
     */
    private void adjustWindow(WebDriver driver) {
        Window window = driver.manage().window();
        Point location = new Point(0, 0);
        LOGGER.info(String.format("Moving browser window to x=%s and y=%s ...", location.getX(), location.getY()));
        window.setPosition(location);
        // FIXME Setzen der Browserfenster-Position funktioniert bei IE sporadisch nicht.
        Dimension dimension = getDefaultSize();
        LOGGER.info(String.format("Resizing browser window to height=%s and width=%s ...", dimension.getHeight(), dimension.getWidth()));
        window.setSize(dimension);
    }

    private Dimension getDefaultSize() {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double defaultWidth = screenSize.getWidth() * 0.6;
        double defaultHeight = screenSize.getHeight() * 0.8;
        return new Dimension((int) defaultWidth, (int) defaultHeight);
    }
    
}
