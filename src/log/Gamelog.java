package log;

import org.apache.log4j.Logger;

public class Gamelog {
    
    private static Logger logger = LoggerUtility.getLogger(Logger.class, "html");

    public static Logger getLogger() {
        return logger;
    }
}
