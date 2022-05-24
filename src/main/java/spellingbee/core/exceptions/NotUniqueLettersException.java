package spellingbee.core.exceptions;

public class NotUniqueLettersException extends Exception {
    public NotUniqueLettersException() {
        super();
    }

    public NotUniqueLettersException(String message) {
        super(message);
    }
}
