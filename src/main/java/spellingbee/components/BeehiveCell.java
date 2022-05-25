package spellingbee.components;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BeehiveCell extends StackPane {
    private String cellValue;
    private Polygon polygon;
    private double edgeWidth;
    private Color backgroundColor;
    private Color outputColor;

    public BeehiveCell(String cellValue, double edgeWidth, Color backgroundColor, Color outputColor) {
        this.cellValue = cellValue;
        this.edgeWidth = edgeWidth;
        this.backgroundColor = backgroundColor;
        this.outputColor = outputColor;

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

    public Color getOutputColor() {
        return outputColor;
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String val) {
        this.cellValue = cellValue;
    }


}
