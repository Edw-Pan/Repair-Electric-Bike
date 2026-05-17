package se.kth.iv1350.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Prints log messages to a file. The log file will be in the 
 * current directory and will be called log.txt.
 */
public class FileLogger {
    private PrintWriter logStream;

    /**
     * Creates a new instance and also creates a new log file.
     * An existing log file will not be deleted.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Prints the specified string to the log file.
     * 
     * @param message The string that will be printed to the log file.
     */
    public void log(String message) {
        logStream.println(createTime() + ", " + message);
    }

    /**
     * Logs an exception by printing its message and stack trace.
     * 
     * @param exception The exception to log.
     */
    public void logException(Exception exception) {
        StringBuilder sb = new StringBuilder();
        sb.append(createTime());
        sb.append(", Exception was thrown: ");
        sb.append(exception.getMessage());
        logStream.println(sb.toString());
        exception.printStackTrace(logStream);
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
