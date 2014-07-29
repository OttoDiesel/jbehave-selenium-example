package example.steps;

import org.jbehave.web.selenium.PerStoryWebDriverSteps;

import example.core.ExecutionConfigurator;

/**
 * Realisierung des Browserfenster-Lebenszyklusses pro Story.
 */
public final class SeleniumWebDriverSteps extends PerStoryWebDriverSteps {
    
    public SeleniumWebDriverSteps() {
        super(ExecutionConfigurator.getInstance().getDriverProvider());
    }
    
}
