package example.steps;

import org.jbehave.core.annotations.Composite;
import org.jbehave.core.annotations.Given;

import example.pages.HomePage;
import example.pages.LoginPage;

public final class LoginSteps extends Steps {
    
    @Given("der User ist eingeloggt auf der Startseite")
    public void userLoggedIn() {
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        if (!loginPage.isLoggedIn()) {
            loginPage.loginStandardUser();
        } else if (!homePage.isHomePage()) {
            homePage.navigateTo();
        }
    }

    @Given("der User ist erfolgreich eingeloggt auf der Startseite")
    @Composite(steps={"Gegeben der User ist eingeloggt auf der Startseite", "Gegeben es wird keine Fehlermeldung angezeigt"})
    public void userLoggedInSuccessfully() {
        // Hier gibt es nichts zum Implementieren.
    }
    
}
