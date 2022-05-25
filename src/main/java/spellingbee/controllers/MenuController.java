package spellingbee.controllers;

import spellingbee.core.data.DataFilter;
import spellingbee.core.data.DataReader;
import spellingbee.core.exceptions.*;
import spellingbee.core.managers.GameManager;
import spellingbee.core.managers.GameService;
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
        GameService manager = new GameManager(dataFilter, dataReader);
        try {
            manager.create();
            model.setErrorPropertyValue("");
        } catch (PangramNotFoundException | IllegalPointRangeException | NotEnoughWordsException e) {
            handleStart();
        }

    }

    private void handleStartWithLetters() {
        GameService manager = new GameManager(dataFilter, dataReader);
        try {
            manager.create(model.getLettersPropertyValue());
            model.setErrorPropertyValue("");
        } catch (PangramNotFoundException | IllegalPointRangeException | NotEnoughWordsException |
                 NotUniqueLettersException | IllegalLettersLengthException | IllegalLetterException e) {
            model.setErrorPropertyValue(e.getMessage());
        }
    }
}
