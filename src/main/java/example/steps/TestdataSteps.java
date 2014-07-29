package example.steps;

import java.util.Map;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.model.ExamplesTable;

public final class TestdataSteps extends Steps {

    /**
     * Definieren der für den Login verfügbaren User.
     * @param user Tabelle mit den Spalten: Name, Username und Passwort.
     */
    @Given("es existieren diese Benutzer: $User")
    public void usersExist(ExamplesTable user) {
        for (Map<String, String> row : user.getRows()) {
            String symbolicName = row.get("Name");
            String username = row.get("Username");
            String password = row.get("Passwort");
            // TODO Testdaten speichern und zugänglich machen.
            logger().debug(String.format("Name = '%s', Username = '%s', Passwort = '%s'", 
                    symbolicName, username, password));
        }
    }

    /**
     * Definieren der vorhandenen Systeme.
     * @param systems Tabelle mit den Spalten: Name und URL
     */
    @Given("diese Systeme sind vorhanden: $Systeme")
    public void systemsExist(ExamplesTable systems) {
        for (Map<String, String> row : systems.getRows()) {
            String name = row.get("Name");
            String url = row.get("URL");
            // TODO Testdaten speichern und zugänglich machen.
            logger().debug(String.format("Name = '%s', URL = '%s'", 
                    name, url));
        }
    }

}
