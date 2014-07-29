package example.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import example.pages.AddressDataPage;
import example.pages.HomePage;

public final class MasterDataSteps extends Steps {
    
    private static final String CHANGE_ADDRESS = "Adressdaten ändern";
    private static final String CHANGE_ACCOUNT = "Kontodaten ändern";

    /**
     * Verwenden einer Funktion der Applikation.
     * @param name entweder 'Adressdaten ändern' oder 'Kontodaten ändern' (ohne Apostrophen)
     */
    @When("der User die Funktion $Name benutzt")
    public void useFunction(String name) {
        assertThat(name, either(equalTo(CHANGE_ADDRESS)).or(equalTo(CHANGE_ACCOUNT)));
        if (CHANGE_ADDRESS.equals(name)) {
            new HomePage().changeAddress();
        } else if (CHANGE_ACCOUNT.equals(name)) {
            new HomePage().changeAccount();
        }
    }
    
    @Then("kann der User seine Adresse ändern")
    public void addressCanBeChanged() {
        // Sehr rudimentäre Verifikation:
        new AddressDataPage().isAddressDataPage();
    }
    
    @Then("kann der User seine Kontodaten ändern")
    public void accountCanBeChanged() {
        throw new IllegalStateException("Exception werfen um zu prüfen, was im Fehlerfall passiert und wie der Report dann aussieht");
    }
    
}
