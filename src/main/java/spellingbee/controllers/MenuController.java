package spellingbee.controllers;

import javafx.scene.Scene;
import spellingbee.App;
import spellingbee.core.data.GameData;
import spellingbee.core.exceptions.*;
import spellingbee.core.managers.GameCreatorManager;
import spellingbee.core.managers.GameCreatorService;
import spellingbee.core.managers.GameManager;
import spellingbee.core.managers.GameService;
import spellingbee.models.GameModel;
import spellingbee.models.MenuModel;
import spellingbee.views.GameView;

import java.util.List;

public class MenuController {
    private MenuModel model;

    public MenuController(MenuModel model) {
        this.model = model;
    }

    public void startWithLetters() {
        handleStartWithLetters();
    }

    public void start() {
        handleStart();
    }

    private void handleStart() {
        GameCreatorService creatorService = new GameCreatorManager(App.getInstance().getDataFilter(), App.getInstance().getDataReader());
        try {
            GameData data = creatorService.create();
            debug(data.getWords(), data.getPangramWords(), data.getLetters());
            model.setErrorPropertyValue("");
            GameService gameService = new GameManager(data);
            GameModel gameModel = new GameModel(data.getLetters());
            GameController gameController = new GameController(gameModel, gameService);
            GameView gameView = new GameView(gameModel, gameController);
            Scene scene = new Scene(gameView.getAsParent());
            scene.getStylesheets().addAll("style.css");
            App.getInstance().setScene(scene);
        } catch (PangramNotFoundException | IllegalPointRangeException | NotEnoughWordsException e) {
            handleStart();
        }

    }

    private void handleStartWithLetters() {
        GameCreatorService creatorService = new GameCreatorManager(App.getInstance().getDataFilter(), App.getInstance().getDataReader());
        try {
            GameData data = creatorService.create(model.getLettersPropertyValue().toLowerCase());
            debug(data.getWords(), data.getPangramWords(), data.getLetters());
            model.setErrorPropertyValue("");
            GameService gameService = new GameManager(data);
            GameModel gameModel = new GameModel(data.getLetters());
            GameController gameController = new GameController(gameModel, gameService);
            GameView gameView = new GameView(gameModel, gameController);
            Scene scene = new Scene(gameView.getAsParent());
            scene.getStylesheets().addAll("style.css");
            App.getInstance().setScene(scene);
        } catch (PangramNotFoundException | IllegalPointRangeException | NotEnoughWordsException |
                 NotUniqueLettersException | IllegalLettersLengthException | IllegalLetterException e) {
            model.setErrorPropertyValue(e.getMessage());
        }
    }

    private void debug(List<String> words, List<String> pangrams, String letters) {
        System.out.println("Letters: " + letters);
        System.out.printf("=== Pangrams ( %d ) ===%n", pangrams.size());
        pangrams.forEach(System.out::println);
        System.out.println("================");
        System.out.printf("=== Words ( %d ) ===%n", words.size());
        words.forEach(System.out::println);
        System.out.println("=============");
    }
}
