package spellingbee.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spellingbee.controllers.MenuController;
import spellingbee.core.constants.Colors;
import spellingbee.core.constants.UINames;
import spellingbee.models.MenuModel;


public class MenuView extends View {
    private final Color BACKGROUND_COLOR = Colors.MENU_BACKGROUND;
    private final String LOGO_PATH = "logo.png";
    private final int LOGO_SIZE = 140;
    private final double PADDING = 50;
    private final MenuModel model;
    private final MenuController controller;
    private boolean status = true;
    private double aspectRatio;
    private final Text title;
    private final Text info;
    private final Text warning;
    private final TextField lettersTextField;
    private final VBox vBox;
    private final HBox hBox;
    private final VBox titleBox;
    private final Button firstButton;
    private final Button secondButton;
    private final Button exitButton;
    private final Image image;
    private final ImageView logoView;

    public MenuView(MenuModel model, MenuController controller) {
        this.model = model;
        this.controller = controller;

        vBox = new VBox();
        hBox = new HBox();

        image = new Image(LOGO_PATH);
        logoView = new ImageView(image);

        titleBox = new VBox();
        title = new Text();
        info = new Text();
        warning = new Text();

        lettersTextField = new TextField();

        firstButton = new Button();
        secondButton = new Button();
        exitButton = new Button();

        init();
    }

    private void init() {

        parent = vBox;
        vBox.setPadding(new Insets(PADDING));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(Background.fill(BACKGROUND_COLOR));

        aspectRatio = image.getWidth() / image.getHeight();
        logoView.setFitWidth(LOGO_SIZE);
        logoView.setFitHeight(LOGO_SIZE * aspectRatio);

        titleBox.setSpacing(10);
        titleBox.setPadding(new Insets(20));
        titleBox.setAlignment(Pos.CENTER);

        title.setText(UINames.MENU_TITLE);
        title.getStyleClass().addAll("title");

        info.setText(UINames.MENU_INFO);
        info.getStyleClass().addAll("info");

        warning.getStyleClass().addAll("info");
        warning.textProperty().bind(model.getErrorProperty());

        model.getLettersProperty().bind(lettersTextField.textProperty());
        lettersTextField.setPromptText(UINames.MENU_FIELD_PROMPT);
        lettersTextField.getStyleClass().addAll("txtfield");
        lettersTextField.setFocusTraversable(false);
        lettersTextField.setVisible(false);

        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 0, 0, 0));


        firstButton.setText(UINames.START_BUTTON);

        secondButton.setText(UINames.CREATE_BUTTON);

        exitButton.setText(UINames.EXIT_BUTTON);

        firstButton.getStyleClass().addAll("btn", "btn-black");
        secondButton.getStyleClass().addAll("btn", "btn-black");
        exitButton.getStyleClass().addAll("btn", "btn-yellow");

        firstButton.setOnMouseClicked(e -> {
            if (status) {
                controller.start();
            } else {
                controller.startWithLetters();
            }
        });

        secondButton.setOnMouseClicked(e -> {
            if (status) {
                lettersTextField.setVisible(true);
                status = !status;
                firstButton.setText(UINames.START_BUTTON);
                secondButton.setText(UINames.CANCEL_BUTTON);
            } else {
                lettersTextField.setVisible(false);
                model.setErrorPropertyValue("");
                lettersTextField.setText("");
                status = !status;
                firstButton.setText(UINames.START_BUTTON);
                secondButton.setText(UINames.CREATE_BUTTON);
            }
        });

        exitButton.setOnMouseClicked(e -> System.exit(0));

        titleBox.getChildren().addAll(
                title,
                info
        );

        hBox.getChildren().addAll(
                firstButton,
                secondButton,
                exitButton
        );

        vBox.getChildren().addAll(
                logoView,
                titleBox,
                lettersTextField,
                hBox,
                warning
        );

    }
}
