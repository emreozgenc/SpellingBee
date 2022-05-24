package spellingbee.core.exceptions;

public class DictionaryDoesNotContainWordException extends Exception {
    public DictionaryDoesNotContainWordException() {
        super();
    }

    public DictionaryDoesNotContainWordException(String message) {
        super(message);
    }
}
