package spellingbee.controllers;

import spellingbee.core.data.DataFilter;
import spellingbee.core.data.DataReader;
import spellingbee.core.data.GameData;
import spellingbee.core.exceptions.*;
import spellingbee.core.managers.GameCreatorManager;
import spellingbee.core.managers.GameCreatorService;
import spellingbee.models.MenuModel;

public class MenuController {
    private MenuModel model;
    private DataReader dataReader;
    private DataFilter dataFilter;

    public MenuController(MenuModel model, DataReader dataReader, DataFilter dataFilter) {
        this.model = model;
        this.dataReader = dataReader;
        this.dataFilter = dataFilter;
    }

    public void start() {
        if (model.getLettersPropertyValue().length() > 0) {
            handleStartWithLetters();
            return;
        }
        handleStart();
    }

    private void handleStart() {
        GameCreatorService creatorService = new GameCreatorManager(dataFilter, dataReader);
        try {
            GameData data = creatorService.create();
            model.setErrorPropertyValue("");
        } catch (PangramNotFoundException | IllegalPointRangeException | NotEnoughWordsException e) {
            handleStart();
        }

    }

    private void handleStartWithLetters() {
        GameCreatorService creatorService = new GameCreatorManager(dataFilter, dataReader);
        try {
            GameData data = creatorService.create(model.getLettersPropertyValue());
            model.setErrorPropertyValue("");
        } catch (PangramNotFoundException | IllegalPointRangeException | NotEnoughWordsException |
                 NotUniqueLettersException | IllegalLettersLengthException | IllegalLetterException e) {
            model.setErrorPropertyValue(e.getMessage());
        }
    }
}
