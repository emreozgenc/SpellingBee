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
import spellingbee.models.MenuModel;

public class MenuView implements View {
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final double LOGO_IMAGE_WIDTH = 200;
    private final double PADDING = 50;
    private final String LOGO_PATH = "logo.png";

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
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(Background.fill(BACKGROUND_COLOR));

        // Logo Image
        Image logoImage = new Image(LOGO_PATH);
        ImageView imageView = new ImageView(logoImage);
        double aspectRatio = logoImage.getWidth() / logoImage.getHeight();
        imageView.setFitWidth(LOGO_IMAGE_WIDTH);
        imageView.setFitHeight(LOGO_IMAGE_WIDTH * aspectRatio);

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
        Button startGameButton = new Button("Oyuna Başla");
        Button createGameButton = new Button("Oyununu Oluştur");
        startGameButton.getStyleClass().addAll("btn");
        createGameButton.getStyleClass().add("btn");

        // Add components to parent
        hBox.getChildren().addAll(
                startGameButton,
                createGameButton
        );

        vBox.getChildren().addAll(
                imageView,
                lettersTextField,
                hBox
        );

    }

    @Override
    public Parent getAsParent() {
        return parent;
    }
}
