package spellingbee.controllers;

import spellingbee.core.exceptions.DictionaryDoesNotContainWordException;
import spellingbee.core.exceptions.IllegalWordLengthException;
import spellingbee.core.exceptions.WordContainsIllegalLetterException;
import spellingbee.core.exceptions.WordDoesNotContainCenterLetterException;
import spellingbee.core.managers.GameService;
import spellingbee.models.GameModel;

public class GameController {
    private GameModel model;
    private GameService gameService;
    public GameController(GameModel model, GameService gameService){
        this.model=model;
        this.gameService=gameService;
    }
}
