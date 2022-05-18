package spellingbee.views;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import spellingbee.controllers.MenuController;
import spellingbee.models.MenuModel;

public class MenuView implements View {
    private final Color BACKGROUND_COLOR = Color.rgb(248, 237, 33);

    private MenuModel model;
    private MenuController controller;
    private Parent parent;

    public MenuView(MenuModel model, MenuController controller) {
        this.model = model;
        this.controller = controller;

        init();
    }

    private void init() {
        VBox vBox = new VBox();
        parent = vBox;

        // To do
    }

    @Override
    public Parent getAsParent() {
        return parent;
    }
}
