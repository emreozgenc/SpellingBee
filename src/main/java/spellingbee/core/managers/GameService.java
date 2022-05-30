package spellingbee.core.managers;

import spellingbee.core.exceptions.*;
import spellingbee.core.results.PointResult;

public interface GameService {
    PointResult check(String inputWord) throws DictionaryDoesNotContainWordException, IllegalWordLengthException, WordContainsIllegalLetterException, WordDoesNotContainCenterLetterException, WordAlreadyFoundException;

    int getMaximumPoint();
}
