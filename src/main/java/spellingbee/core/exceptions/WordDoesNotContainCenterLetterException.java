package spellingbee.core.exceptions;

public class WordDoesNotContainCenterLetterException extends Exception {
    public WordDoesNotContainCenterLetterException() {
        super();
    }

    public WordDoesNotContainCenterLetterException(String message) {
        super(message);
    }
}
