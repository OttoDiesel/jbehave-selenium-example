package example.core;

import static example.report.FormatWithLog.CONSOLE_WITH_LOG;
import static example.report.FormatWithLog.HTML_WITH_LOG;
import static example.report.FormatWithLog.TXT_WITH_LOG;
import static example.report.FormatWithLog.XML_WITH_LOG;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.awt.Toolkit;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.context.Context;
import org.jbehave.core.context.ContextView;
import org.jbehave.core.context.JFrameContextView;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.ConsoleOutput;
import org.jbehave.core.reporters.ContextOutput;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ContextStepMonitor;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContextStoryReporter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

/**
 * Hier wird die JBehave-Testdurchführung konfiguriert.
 */
public final class ExecutionConfigurator {
    
    private static ExecutionConfigurator instance;
    
    private static final Logger LOGGER = Logger.getLogger(ExecutionConfigurator.class);
    
    private final MyWebDriverProvider driverProvider = new MyWebDriverProvider();
    
    private final Context context = new Context();
    private final Format contextFormat = new ContextOutput(this.context);
    private final ContextView contextView = createContextView();
    private final CrossReference xref = new CrossReference();
    private final ContextStepMonitor stepMonitor = new ContextStepMonitor(this.context, this.contextView, this.xref.getStepMonitor());
    
    private Configuration configuration;
    
    private ExecutionConfigurator() {
        // Singleton
    }
    
    public static ExecutionConfigurator getInstance() {
        if (instance == null) {
            instance = new ExecutionConfigurator();
        }
        return instance;
    }
    
    public MyWebDriverProvider getDriverProvider() {
        return this.driverProvider;
    }
    
    public StoryReporterBuilder getStoryReportBuilder() {
        return this.configuration.storyReporterBuilder();
    }
    
    private JFrameContextView createContextView() {
        Dimension size = getDefaultSize();
        int width = size.getWidth();
        int height = size.getHeight();
        Point location = getDefaultSizeLocation();
        int xPos = location.getX();
        int yPos = location.getY();
        LOGGER.debug(String.format("Creating context view with width=%s, height=%s, x-location=%s and y-location=%s ...", 
                width, height, xPos, yPos));
        return new JFrameContextView().sized(width, height).located(xPos, yPos);
    }
    
    private Dimension getDefaultSize() {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double defaultWidth = screenSize.getWidth() * 0.25;
        double defaultHeight = screenSize.getHeight() * 0.125;
        return new Dimension((int) defaultWidth, (int) defaultHeight);
    }
    
    private Point getDefaultSizeLocation() {
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int defaultWidth = getDefaultSize().getWidth();
        return new Point(screenWidth - defaultWidth, 0);
    }
    
    /**
     * Initialisieren der JBehave-Konfiguration inkl. Selenium-spezifischer Dinge.
     */
    public void initialize(Class<? extends Embeddable> embeddableClass) {
        assertThat("Configuration is already initialized", this.configuration, nullValue());
        // Es ist aufwändiger als gedacht, die Locale auf Deutsch zu ändern:
        Keywords keywords = new LocalizedKeywords(Locale.GERMAN);
        SeleniumConfiguration seleniumConf = new SeleniumConfiguration().useWebDriverProvider(getDriverProvider());
        StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder()
            .withCodeLocation(codeLocationFromClass(embeddableClass))
            .withReporters(new SeleniumContextStoryReporter(seleniumConf.seleniumContext()))
            //.withFailureTrace(true) // Ist ein übersichtlicherer oder detaillierterer Report gewünscht?
            .withCrossReference(this.xref).withDefaultFormats()
            .withFormats(this.contextFormat, CONSOLE_WITH_LOG, HTML_WITH_LOG, XML_WITH_LOG, TXT_WITH_LOG)
            .withKeywords(keywords);
        this.configuration = seleniumConf
            .useStoryLoader(new LoadFromClasspath(embeddableClass))
            .useParameterControls(new ParameterControls().useDelimiterNamedParameters(true))
            .useStoryReporterBuilder(storyReporterBuilder)
            .useStepMonitor(this.stepMonitor)
            .useKeywords(keywords)
            .useStepCollector(new MarkUnmatchedStepsAsPending(keywords))
            .useStoryParser(new RegexStoryParser(keywords))
            .useDefaultStoryReporter(new ConsoleOutput(keywords));
    }
    
    public Configuration getConfiguration() {
        return this.configuration;
    }

}
