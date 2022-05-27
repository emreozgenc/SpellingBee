package spellingbee;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import spellingbee.controllers.GameController;
import spellingbee.controllers.MenuController;
import spellingbee.core.data.DataReader;
import spellingbee.core.data.TxtReader;
import spellingbee.core.data.WordFilter;
import spellingbee.models.GameModel;
import spellingbee.models.MenuModel;
import spellingbee.views.GameView;
import spellingbee.views.MenuView;

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
        MenuModel model = new MenuModel();
        MenuController controller = new MenuController(model, new TxtReader(), new WordFilter());
        MenuView view = new MenuView(model, controller);
        Scene scene = new Scene(view.getAsParent());
        scene.getStylesheets().addAll("style.css");
        stage.setTitle("Spelling Bee v0.0.1");
        stage.setScene(scene);
        stage.show();
    }

    public static App getInstance() {
        return instance;
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }

}