package spellingbee.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import spellingbee.controllers.GameController;
import spellingbee.core.constants.UINames;
import spellingbee.models.GameModel;

public class GameView {
    private GameModel model;
    private GameController controller;
    private final double EDGE = 100;
    private Parent parent;
    public  GameView(GameModel model, GameController controller){
        this.model = model;
        this.controller= controller;
        init();
    }
    private void init() {
        HBox hBox = new HBox();
        parent = hBox;
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        VBox rightVBox = new VBox();
        rightVBox.setSpacing(10);

        VBox leftVBox = new VBox();

        HBox hBoxPoint = new HBox();

        HBox hBoxType = new HBox();
        hBoxType.setAlignment(Pos.CENTER);

        HBox hBoxHexagons = new HBox();

        HBox hBoxButtons = new HBox();
        hBoxButtons.setPadding(new Insets(20, 10, 20, 10));
        hBoxButtons.setAlignment(Pos.CENTER);
        hBoxButtons.setSpacing(5);

        TextField textField = new TextField();
        textField.setPromptText("Yaz");

        Button deleteButton = new Button(UINames.DELETE_BUTTON);
        deleteButton.getStyleClass().addAll("btn", "btn-white");

        Button refreshButton = new Button(UINames.SHUFFLE_BUTTON);
        refreshButton.getStyleClass().addAll("btn", "btn-white");

        Button enterButton = new Button(UINames.ENTER_BUTTON);
        enterButton.getStyleClass().addAll("btn", "btn-white");

        Text pointText = new Text("Puan: 5");

        ListView<String> words= new ListView<>();


        hBoxPoint.getChildren().add(pointText);

        hBoxType.getChildren().add(textField);

        hBoxButtons.getChildren().addAll(deleteButton,refreshButton,enterButton);

        rightVBox.getChildren().addAll(pointText, words);

        leftVBox.getChildren().addAll(hBoxType,hBoxHexagons,hBoxButtons);

        hBox.getChildren().addAll(leftVBox,rightVBox);

    }
    public Parent getAsParent(){
        return  parent;
    }


}
