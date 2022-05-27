package spellingbee.core.managers;

import spellingbee.core.exceptions.DictionaryDoesNotContainWordException;
import spellingbee.core.exceptions.IllegalWordLengthException;
import spellingbee.core.exceptions.WordContainsIllegalLetterException;
import spellingbee.core.exceptions.WordDoesNotContainCenterLetterException;
import spellingbee.core.results.PointResult;

public interface GameService {
    PointResult check(String inputWord) throws DictionaryDoesNotContainWordException, IllegalWordLengthException, WordContainsIllegalLetterException, WordDoesNotContainCenterLetterException;
}
