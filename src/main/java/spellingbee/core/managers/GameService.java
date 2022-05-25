package spellingbee.core.managers;

import spellingbee.core.exceptions.DictionaryDoesNotContainWordException;
import spellingbee.core.exceptions.IllegalWordLengthException;
import spellingbee.core.exceptions.WordContainsIllegalLetterException;
import spellingbee.core.exceptions.WordDoesNotContainCenterLetterException;

public interface GameService {
    int check(String inputWord) throws DictionaryDoesNotContainWordException, IllegalWordLengthException, WordContainsIllegalLetterException, WordDoesNotContainCenterLetterException;
}
