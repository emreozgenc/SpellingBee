package spellingbee.core.exceptions;

public class WordAlreadyFoundException extends Exception {
    public WordAlreadyFoundException() {
        super();
    }

    public WordAlreadyFoundException(String message) {
        super(message);
    }
}
