package spellingbee.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import spellingbee.controllers.GameController;
import spellingbee.models.GameModel;

public class GameView {
    private GameModel model;
    private GameController controller;
    private Double[] Hexagon ={
        200.0, 50.0,
                300.0, 50.0,
                350.0, 150.0,
                300.0, 250.0,
                200.0, 250.0,
                150.0, 150.0,
    };
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


        Polygon polygon = new Polygon();
        polygon.setFill(Color.YELLOW);

        TextField textField = new TextField();

        Button deleteButton = new Button("Delete");

        Button refreshButton = new Button("Refresh");

        Button enterButton = new Button("Enter");

        Text pointText = new Text("Puan: 5");

        ListView<String> words= new ListView<>();


        hBoxPoint.getChildren().add(pointText);

        hBoxType.getChildren().add(textField);

        hBoxHexagons.getChildren().addAll(polygon);

        polygon.getPoints().addAll(Hexagon);

        hBoxButtons.getChildren().addAll(deleteButton,refreshButton,enterButton);

        rightVBox.getChildren().addAll(hBoxType,hBoxHexagons,hBoxButtons);
        leftVBox.getChildren().addAll(words,hBoxPoint);

        hBox.getChildren().addAll(rightVBox,leftVBox);

    }
    public Parent getAsParent(){
        return  parent;
    }


}
