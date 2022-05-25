package spellingbee.core.managers;

import spellingbee.core.data.GameData;
import spellingbee.core.exceptions.*;

public interface GameCreatorService {
    GameData create(String letters) throws PangramNotFoundException, NotEnoughWordsException, IllegalPointRangeException, IllegalLettersLengthException, NotUniqueLettersException, IllegalLetterException;

    GameData create() throws PangramNotFoundException, NotEnoughWordsException, IllegalPointRangeException;
}
