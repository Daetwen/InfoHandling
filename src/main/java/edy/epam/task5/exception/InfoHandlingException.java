package edy.epam.task5.exception;

public class InfoHandlingException extends Exception {
    public InfoHandlingException() {
    }

    public InfoHandlingException(String message) {
        super(message);
    }

    public InfoHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfoHandlingException(Throwable cause) {
        super(cause);
    }
}
