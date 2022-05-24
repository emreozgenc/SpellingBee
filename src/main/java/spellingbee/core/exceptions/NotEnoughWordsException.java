package spellingbee.core.exceptions;

public class NotEnoughWordsException extends Exception {
    public NotEnoughWordsException() {
        super();
    }

    public NotEnoughWordsException(String message) {
        super(message);
    }
}
