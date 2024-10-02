import java.io.*;

public class logger {
    private static logger instance;
    private File logFile;

    private logger() {
        logFile = new File("log.txt");
    }

    public static logger getInstance() {
        if (instance == null) {
            instance = new logger();
        }
        return instance;
    }

    public void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.err.println("Error logging message: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        logger logger = new logger();
        logger.log("This is a log message");
        logger.log("This is another log message");
    }
}