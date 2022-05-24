package spellingbee.core.managers;

import spellingbee.core.exceptions.IllegalPointRangeException;
import spellingbee.core.exceptions.NotEnoughWordsException;
import spellingbee.core.exceptions.PangramNotFoundException;

public interface GameService {
    void create(String letters) throws PangramNotFoundException, NotEnoughWordsException, IllegalPointRangeException;

    void create() throws PangramNotFoundException, NotEnoughWordsException, IllegalPointRangeException;
}
