package spellingbee;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("To do");
        FlowPane parent = new FlowPane(label);
        parent.setAlignment(Pos.CENTER);
        stage.setTitle("Spelling Bee v0.0.1");
        stage.setScene(new Scene(parent, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}