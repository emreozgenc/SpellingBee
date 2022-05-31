package spellingbee.views;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import spellingbee.components.Beehive;
import spellingbee.components.BeehiveCell;
import spellingbee.controllers.GameController;
import spellingbee.core.constants.Colors;
import spellingbee.core.constants.UINames;
import spellingbee.models.GameModel;

public class GameView extends View {
    private GameModel model;
    private GameController controller;
    private String letters;
    private StringBuilder input;

    /* Components */
    private final HBox parentHBox;
    private final VBox leftVBox;
    private final VBox rightVBox;
    private final HBox buttonsHBox;
    private final HBox leftInnerTopHBox;
    private final HBox inputBox;
    private final HBox returnHBox;
    private final Beehive beehive;
    private final ListView<String> foundWordsList;
    private final Text pointerText;
    private final Text statusText;
    private final Text pointText;
    private final Button enterButton;
    private final Button shuffleButton;
    private final Button returnButton;
    private final Text returnText;
    private final ImageView returnButtonGraphic;
    private final ImageView shuffleButtonGraphic;
    private final Button deleteButton;

    private SequentialTransition blinkAnimation;
    private SequentialTransition shakeAnimation;


    public GameView(GameModel model, GameController controller) {
        this.letters = model.letters;
        this.model = model;
        this.controller = controller;
        input = new StringBuilder();

        parentHBox = new HBox();
        parent = parentHBox;

        leftVBox = new VBox();
        rightVBox = new VBox();
        buttonsHBox = new HBox();
        leftInnerTopHBox = new HBox();
        inputBox = new HBox();
        foundWordsList = new ListView<>();
        pointerText = new Text();
        statusText = new Text();
        pointText = new Text();
        enterButton = new Button();
        shuffleButton = new Button();
        deleteButton = new Button();
        shuffleButtonGraphic = new ImageView();
        returnButtonGraphic = new ImageView();
        returnButton = new Button();
        returnHBox = new HBox();
        returnText = new Text();

        beehive = new Beehive(this.letters.toUpperCase());

        editComponents();
        assignEvents();
        initAnimations();
    }

    private void initAnimations() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), pointerText);
        fadeOut.setToValue(0);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), pointerText);
        fadeIn.setToValue(1);

        blinkAnimation = new SequentialTransition(fadeOut, fadeIn);
        blinkAnimation.setCycleCount(Animation.INDEFINITE);
        blinkAnimation.play();

        TranslateTransition moveRight = new TranslateTransition(Duration.millis(150), statusText);
        moveRight.setToX(20);

        TranslateTransition moveLeft = new TranslateTransition(Duration.millis(150), statusText);
        moveLeft.setToX(-20);

        TranslateTransition moveCenter = new TranslateTransition(Duration.millis(150), statusText);
        moveCenter.setToX(0);

        shakeAnimation = new SequentialTransition(moveRight, moveLeft, moveCenter);
    }

    private void editComponents() {
        parentHBox.setPadding(new Insets(30));
        parentHBox.setSpacing(30);
        parentHBox.setAlignment(Pos.CENTER);
        parentHBox.setBackground(Background.fill(Color.WHITE));

        leftVBox.setPadding(new Insets(10));
        leftVBox.setSpacing(20);
        leftVBox.setAlignment(Pos.TOP_CENTER);

        leftInnerTopHBox.setAlignment(Pos.CENTER);

        inputBox.setAlignment(Pos.CENTER);

        pointerText.getStyleClass().add("pointer");
        pointerText.setText("|");
        pointerText.setFill(Colors.CELL_CENTER_OUTPUT);

        leftInnerTopHBox.getChildren().addAll(
                inputBox,
                pointerText
        );

        buttonsHBox.setSpacing(5);
        buttonsHBox.setAlignment(Pos.CENTER);

        enterButton.getStyleClass().addAll("btn", "btn-white", "btn-game");
        enterButton.setText(UINames.ENTER_BUTTON);
        enterButton.setFocusTraversable(true);
        shuffleButton.getStyleClass().addAll("btn", "btn-white", "btn-game");
        shuffleButton.setFocusTraversable(false);
        deleteButton.getStyleClass().addAll("btn", "btn-white", "btn-game");
        deleteButton.setFocusTraversable(false);
        deleteButton.setText(UINames.DELETE_BUTTON);

        shuffleButtonGraphic.setImage(new Image("shuffle.png"));
        shuffleButtonGraphic.setFitWidth(15);
        shuffleButtonGraphic.setFitHeight(15);
        shuffleButton.setGraphic(shuffleButtonGraphic);

        buttonsHBox.getChildren().addAll(
                deleteButton,
                shuffleButton,
                enterButton
        );

        statusText.getStyleClass().add("status-text");

        leftVBox.getChildren().addAll(
                leftInnerTopHBox,
                beehive,
                buttonsHBox,
                statusText
        );

        rightVBox.setPadding(new Insets(10));
        rightVBox.setSpacing(10);

        pointText.getStyleClass().add("point-text");
        pointText.setText(UINames.POINT_LABEL + 0 + " / " + model.getMaximumPointPropertyValue());

        foundWordsList.setFocusTraversable(false);
        foundWordsList.getStyleClass().add("word-list");

        returnHBox.setAlignment(Pos.CENTER);
        returnHBox.setSpacing(10);

        returnText.setText(UINames.RETURN_MENU);
        returnText.getStyleClass().add("status-text");

        returnButton.setFocusTraversable(false);
        returnButton.getStyleClass().addAll("btn", "btn-white", "btn-game");
        returnButton.setGraphic(returnButtonGraphic);

        returnButtonGraphic.setImage(new Image("return.png"));
        returnButtonGraphic.setFitHeight(15);
        returnButtonGraphic.setFitWidth(15);

        returnHBox.getChildren().addAll(
                returnButton,
                returnText
        );

        rightVBox.getChildren().addAll(
                pointText,
                foundWordsList,
                returnHBox
        );

        parentHBox.getChildren().addAll(
                leftVBox,
                rightVBox
        );


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

            for (BeehiveCell cell : cells) {
                if (cell.getCellValue().equalsIgnoreCase(ch)) {
                    cell.playClickAnimation();
                    break;
                }
            }

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

        statusText.textProperty().bind(model.getStatusProperty());

        model.getStatusProperty().addListener((o, n, t) -> {
            shakeAnimation.play();
        });

        model.getResultWordProperty().addListener((o, n, t) -> {
            String str = String.format("%s (%d puan)", model.getResultWordPropertyValue(), model.getPointPropertyValue());
            foundWordsList.getItems().add(str);
            foundWordsList.scrollTo(str);
        });

        model.getCurrentPointProperty().addListener((o, n, t) -> {
            pointText.setText(UINames.POINT_LABEL + model.getCurrentPointPropertyValue() + " / " + model.getMaximumPointPropertyValue());
        });

        shuffleButton.setOnAction(e -> {
            beehive.shuffle();
        });

        enterButton.setOnAction(e -> {
            model.setWordPropertyValue(input.toString().toLowerCase());
            controller.check();
            inputBox.getChildren().clear();
            input.delete(0, input.length());
        });

        deleteButton.setOnAction(e -> {
            if (input.length() > 0 && inputBox.getChildren().size() > 0) {
                inputBox.getChildren().remove(inputBox.getChildren().size() - 1);
                input.delete(input.length() - 1, input.length());
            }
        });

        returnButton.setOnAction(e -> {
            controller.returnMenu();
        });
    }

    private void addLetterToInputBox(String letter, Color color) {
        if (letter.length() != 1) return;
        if (!Character.isLetter(letter.charAt(0))) return;

        ObservableList<Node> children = inputBox.getChildren();
        letter = letter.toUpperCase();

        Text text = new Text(letter);
        text.setFill(color);
        text.getStyleClass().addAll("inputbox-text");

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

}
