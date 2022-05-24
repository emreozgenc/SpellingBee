package spellingbee.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import spellingbee.controllers.MenuController;
import spellingbee.core.constants.UINames;
import spellingbee.models.MenuModel;

public class MenuView implements View {
    private final Color BACKGROUND_COLOR = Color.rgb(255, 209, 0);
    private final double PADDING = 50;
    private MenuModel model;
    private MenuController controller;
    private Parent parent;

    public MenuView(MenuModel model, MenuController controller) {
        this.model = model;
        this.controller = controller;

        init();
    }

    private void init() {

        // Parent
        VBox vBox = new VBox();
        parent = vBox;
        vBox.setPadding(new Insets(PADDING));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(Background.fill(BACKGROUND_COLOR));

        // Text Field
        TextField lettersTextField = new TextField();
        lettersTextField.setPromptText("Oluşturmak için harfleri gir...");
        lettersTextField.getStyleClass().addAll("txtfield");

        // HBox
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 0, 0, 0));

        // Buttons
        Button startButton = new Button(UINames.START_BUTTON);
        Button exitButton = new Button(UINames.EXIT_BUTTON);
        startButton.getStyleClass().addAll("btn", "btn-black");
        exitButton.getStyleClass().addAll("btn", "btn-yellow");

        // Add components to parent
        hBox.getChildren().addAll(
                startButton,
                exitButton
        );

        vBox.getChildren().addAll(
                lettersTextField,
                hBox
        );

    }

    @Override
    public Parent getAsParent() {
        return parent;
    }
}
