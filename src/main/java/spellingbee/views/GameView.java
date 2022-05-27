package spellingbee.views;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spellingbee.components.Beehive;
import spellingbee.components.BeehiveCell;
import spellingbee.controllers.GameController;
import spellingbee.core.constants.Colors;
import spellingbee.core.constants.UINames;
import spellingbee.models.GameModel;

public class GameView {
    private GameModel model;
    private GameController controller;
    private Beehive beehive;
    private Parent parent;
    private HBox inputBox;
    private String letters = "kayseri";
    private StringBuilder input;

    public GameView(GameModel model, GameController controller) {
        this.model = model;
        this.controller = controller;
        input = new StringBuilder();
        initBeeHive();
        init();
        assignEvents();
    }

    private void assignEvents() {
        BeehiveCell[] cells = beehive.getCells();

        for (BeehiveCell cell : cells) {
            cell.getPolygon().setOnMouseClicked(e -> {
                cell.playClickAnimation();
                addLetterToInputBox(cell.getCellValue(), cell.getOutputColor());
            });
        }

        String centerCharacter = Character.toString(letters.charAt(letters.length() / 2));

        parent.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            if (keyCode == KeyCode.BACK_SPACE) {
                removeLetterFromInputBox();
                return;
            }
        });

        parent.setOnKeyTyped(e -> {
            String ch = e.getCharacter().toLowerCase();

            if (centerCharacter.equalsIgnoreCase(ch)) {
                addLetterToInputBox(ch, Colors.CELL_CENTER_OUTPUT);
                return;
            }

            if (letters.contains(ch)) {
                addLetterToInputBox(ch, Colors.CELL_OUTPUT);
                return;
            }

            addLetterToInputBox(ch, Colors.WRONG_OUTPUT);
        });


    }

    private void init() {
        HBox hBox = new HBox();
        parent = hBox;
        hBox.setSpacing(30);
        hBox.setPadding(new Insets(30));
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(Background.fill(Color.WHITE));

        VBox rightVBox = new VBox();
        rightVBox.setSpacing(10);

        VBox leftVBox = new VBox();

        HBox hBoxPoint = new HBox();

        HBox innerTopHBox = new HBox();

        inputBox = new HBox();
        inputBox.setAlignment(Pos.CENTER);

        Text pointer = new Text("|");
        pointer.setFill(Colors.CELL_CENTER_OUTPUT);
        pointer.setStyle("-fx-font-size: 25;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: 700;");

        innerTopHBox.getChildren().addAll(inputBox, pointer);
        innerTopHBox.setAlignment(Pos.TOP_CENTER);
        innerTopHBox.setPadding(new Insets(0, 0, 10, 0));

        HBox hBoxButtons = new HBox();
        hBoxButtons.setPadding(new Insets(20, 10, 20, 10));
        hBoxButtons.setAlignment(Pos.CENTER);
        hBoxButtons.setSpacing(5);

        Button deleteButton = new Button(UINames.DELETE_BUTTON);
        deleteButton.getStyleClass().addAll("btn", "btn-white", "btn-game");
        deleteButton.setFocusTraversable(false);
        deleteButton.setOnAction(e -> {
            inputBox.getChildren().clear();
            input.delete(0, input.length());
        });

        Button refreshButton = new Button(UINames.SHUFFLE_BUTTON);
        refreshButton.setOnAction(e -> beehive.shuffle());
        refreshButton.getStyleClass().addAll("btn", "btn-white", "btn-game");
        refreshButton.setFocusTraversable(false);

        Button enterButton = new Button(UINames.ENTER_BUTTON);
        enterButton.getStyleClass().addAll("btn", "btn-white", "btn-game");
        enterButton.setOnAction(e -> {
            System.out.println(input.toString());
            input.delete(0, input.length());
            inputBox.getChildren().clear();
        });

        Text pointText = new Text("Puan: 5");

        ListView<String> words = new ListView<>();


        hBoxPoint.getChildren().add(pointText);

        hBoxButtons.getChildren().addAll(deleteButton, refreshButton, enterButton);

        rightVBox.getChildren().addAll(pointText, words);

        leftVBox.getChildren().addAll(innerTopHBox, beehive, hBoxButtons);

        hBox.getChildren().addAll(leftVBox, rightVBox);

    }

    private void initBeeHive() {
        beehive = new Beehive("kayseri".toUpperCase());
    }

    private void addLetterToInputBox(String letter, Color color) {
        if (letter.length() != 1) return;
        if (!Character.isLetter(letter.charAt(0))) return;

        ObservableList<Node> children = inputBox.getChildren();
        letter = letter.toUpperCase();

        Text text = new Text(letter);
        text.setFill(color);
        text.setStyle("-fx-font-family: Arial;" +
                "-fx-font-weight:700;" +
                "-fx-font-size:20;");

        children.add(text);
        input.append(letter);

        if (input.length() > 20) {
            children.clear();
            input.delete(0, input.length());
        }
    }

    private void removeLetterFromInputBox() {
        ObservableList<Node> children = inputBox.getChildren();
        int size = children.size();

        if (size == 0) return;

        children.remove(size - 1);
        input.deleteCharAt(input.length() - 1);
    }

    public Parent getAsParent() {
        return parent;
    }


}
