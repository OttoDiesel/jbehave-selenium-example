package example.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.jbehave.core.annotations.Given;

import example.pages.PageObject;

public final class ErrorHandlingSteps extends Steps {
    
    @Given("es wird keine Fehlermeldung angezeigt")
    public void noErrorMessage() {
        assertThat(new PageObject().isErrorDisplayed(), equalTo(false));
    }

    @Given("die Systemdaten werden zurückgesetzt")
    public void resetSystemData() {
        logger().info("Datenbank und Verzeichnisse werden geputzt ...");
        // Man denke sich hierzu einen entsprechenden Code.
    }
}
