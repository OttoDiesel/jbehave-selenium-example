package example.core;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.jbehave.core.io.StoryFinder;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * Lokalisieren der Steps dynamisch per Dependency Injection.
 */
public final class StepLocator extends AbstractModule {

    private static final Logger LOGGER = Logger.getLogger(StepLocator.class);
    
    private String[] packagePatterns;

    public StepLocator(String... packagePatterns) {
        this.packagePatterns = packagePatterns;
    }

    @Override
    protected void configure() {
        bindMatchingSteps();
    }

    private void bindMatchingSteps() {
        List<String> includes = new ArrayList<String>();
        List<String> excludes = Collections.emptyList();
        URL codeLocationFromClass = codeLocationFromClass(this.getClass());
        for (String packagePattern : this.packagePatterns) {
            includes.add(packagePattern + "/*.class");
        }
        List<String> stepNames = new ArrayList<String>();
        stepNames.addAll(new StoryFinder().findPaths(codeLocationFromClass, includes, excludes));
        for (String stepName : stepNames) {
            // filter-out inner classes:
            if (!stepName.contains("$")) {
                String className = convertFilepathToClassname(stepName);
                try {
                    LOGGER.trace("Located step class: " + className);
                    bind(Class.forName(className)).in(Scopes.SINGLETON);
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("Could not find Step classes", e);
                }
            }
        }
    }

    private String convertFilepathToClassname(String stepName) {
        String className = stepName.replaceAll("/", ".");
        int endIndex = stepName.length() - ".class".length();
        return className.substring(0, endIndex);
    }

}
