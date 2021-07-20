package edy.epam.task5.reader;

import edy.epam.task5.exception.InfoHandlingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class TextReader {
    private static final Logger logger = LogManager.getLogger();

    public String readText(String filePath) throws InfoHandlingException {
        try {
            Path path = Path.of(filePath);
            return Files.readString(path);
        } catch (InvalidPathException | IOException e) {
            logger.error("Error reading from file: " + e);
            throw new InfoHandlingException("Error reading from file: " + e);
        }
    }
}
