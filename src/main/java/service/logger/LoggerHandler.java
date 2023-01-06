package service.logger;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static service.UtilityClass.isStringEmpty;

/**
 * The class provides a method that creates a logger for a certain class
 * and returns an instance of the logger
 *
 * Each class in the 'service' package has its own logger to keep track of
 * errors that can occur during the executing. We log errors only
 */
public final class LoggerHandler {

    // in case getLogger method wouldn't work
    private static final Logger logHandler = Logger.getLogger(LoggerHandler.class.getSimpleName());

    public static Optional<Logger> getLogger(String className) {

        if (isStringEmpty(className)) {
            logHandler.log(Level.SEVERE, "Variable is empty.");
        }

        Logger logr = Logger.getLogger(className);
        try {
            FileHandler fh = new FileHandler(String.format("src/main/java/service/logger/%s.log", className.toLowerCase()), true);
            fh.setLevel(Level.SEVERE);
            logr.addHandler(fh);

        } catch (IOException e) {
            logHandler.log(Level.SEVERE, "File logger not working.", e);
        }

        return Optional.ofNullable(logr);
    }
}
