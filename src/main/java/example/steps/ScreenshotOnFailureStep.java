package example.steps;

import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;

import example.core.ExecutionConfigurator;

/**
 * Dieser Step sorgt dafür, dass bei Fehlern automatisch Screenshots erstellt werden.
 * Leider sind die Namen der Screenshots nicht optimal, da nicht hervorgeht,
 * welche Story, welches Szenario und welcher Step den Fehler verursacht hat.
 * Das ließe sich verbessern, indem man Methoden aus der Superklasse
 * überschreibt (inkl. Annotation) und einen eigenen StoryReporter dafür implementiert.
 * Er weiss nämlich, welche Story und welches Szenario gerade läuft, sodass man
 * diese Information in den Dateinamen einfügen kann.
 */
public final class ScreenshotOnFailureStep extends WebDriverScreenshotOnFailure {
    
    public ScreenshotOnFailureStep() {
        super(ExecutionConfigurator.getInstance().getDriverProvider(), 
                ExecutionConfigurator.getInstance().getStoryReportBuilder());
    }
    
}
