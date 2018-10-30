package com.ideas2it.dvdstore.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * <p>
 * The {@code DvdStoreLogger} class provides a common logger for the 
 * DVD Store. It makes use of Apache Log4j for logging.
 * </p>
 *
 */
public class DvdStoreLogger {
    private static Logger logger = LogManager.getRootLogger();

    public static void debug(Object message) {
        logger.debug(message);
    }

    public static void info(Object message) {
        logger.info(message);
    }

    public static void warn(Object message) {
        logger.warn(message);
    }

    public static void error(Object message) {
        logger.error(message);
    }

    public static void error(Object message, Throwable cause) {
        logger.error(message, cause);
    }

    public static void fatal(Object message) {
        logger.fatal(message);
    }

}
