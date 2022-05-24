package spellingbee.core.exceptions;

public class WordContainsIllegalLetterException extends Exception {
    public WordContainsIllegalLetterException() {
        super();
    }

    public WordContainsIllegalLetterException(String message) {
        super(message);
    }
}
