package spellingbee.components;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BeehiveCell extends StackPane {
    private String cellValue;
    private Polygon polygon;
    private double edgeWidth;

    public BeehiveCell(String cellValue, double edgeWidth) {
        this.cellValue = cellValue;
        this.edgeWidth = edgeWidth;

        init();
    }

    private void init() {
        polygon = new Polygon(new double[]{
                0.0, 0.0,
                edgeWidth, 0.0,
                1.5 * edgeWidth, 0.5 * edgeWidth * Math.sqrt(3),
                edgeWidth, edgeWidth * Math.sqrt(3),
                0.0, edgeWidth * Math.sqrt(3),
                -0.5 * edgeWidth, 0.5 * edgeWidth * Math.sqrt(3)
        });

        Label cellLabel = new Label(cellValue);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(5);
        polygon.setFill(Color.WHITE);
        getChildren().addAll(polygon, cellLabel);
    }


}
