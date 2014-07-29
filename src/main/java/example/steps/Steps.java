package example.steps;

import org.apache.log4j.Logger;

/**
 * Superklasse für alle Steps (macht in diesem kleinen Beispiel eher wenig Sinn).
 */
public class Steps {

    private final Logger logger = Logger.getLogger(getClass());
    
    protected Logger logger() {
        return this.logger;
    }
    
}
