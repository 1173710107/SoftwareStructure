package testLog4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class testLog4j {
  public static void main(String argv[]) {
    // Create a logger by the name of class Log4jTest.

    // Use the default configuration.
    // BasicConfigurator.configure();
    // Set the logger level to Level.INFO
    Logger logger = Logger.getLogger(testLog4j.class);
    logger.setLevel(Level.INFO);
    // This request will be disabled since Level.DEBUG < Level.INFO.
    logger.debug("This is debug.");
    // These requests will be enabled.
    logger.info("This is an info.");
    logger.warn("This is a warning.");
    logger.error("This is an error.");
    logger.fatal("This is a fatal error.");
    return;
  }

}
