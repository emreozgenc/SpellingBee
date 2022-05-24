package spellingbee;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import spellingbee.controllers.GameController;
import spellingbee.core.data.TxtReader;
import spellingbee.models.GameModel;
import spellingbee.views.GameView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


public class App extends Application {

    private static App instance = null;
    private Stage stage = null;

    @Override
    public void init() {
        instance = this;
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        GameModel model = new GameModel();
        GameController controller = new GameController(model);
        GameView view = new GameView(model, controller);
        stage.setScene(new Scene(view.getAsParent()));
        stage.show();
    }

    public static App getInstance() {
        return instance;
    }

    public void setSceneAsGame() {
        // To do
    }

    public void setSceneAsMenu() {
        // To do
    }

    public static void main(String[] args) {
        TxtReader reader = new TxtReader();
        List<String> words = reader.read();

        for(int i=0;i<100;i++) {
            System.out.println(words.get(i));
        }
        launch();

    }

}