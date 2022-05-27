package spellingbee.controllers;

import spellingbee.core.exceptions.DictionaryDoesNotContainWordException;
import spellingbee.core.exceptions.IllegalWordLengthException;
import spellingbee.core.exceptions.WordContainsIllegalLetterException;
import spellingbee.core.exceptions.WordDoesNotContainCenterLetterException;
import spellingbee.core.managers.GameService;
import spellingbee.core.results.PointResult;
import spellingbee.models.GameModel;

public class GameController {
    private GameModel model;
    private GameService gameService;

    public GameController(GameModel model, GameService gameService) {
        this.model = model;
        this.gameService = gameService;
    }

    public void check() {
        try {
            PointResult pointResult = gameService.check(model.getWordPropertyValue());
            model.setPointPropertyValue(pointResult.getPoint());
            model.setResultWordPropertyValue(pointResult.getWord());
            model.setCurrentPointPropertyValue(pointResult.getCurrentPoint());
        } catch (DictionaryDoesNotContainWordException | IllegalWordLengthException |
                 WordContainsIllegalLetterException | WordDoesNotContainCenterLetterException e) {
            model.setErrorPropertyValue(e.getMessage());
        }
    }
}
