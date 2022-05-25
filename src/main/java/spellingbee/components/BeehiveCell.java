package spellingbee.components;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class BeehiveCell extends StackPane {
    private String cellValue;
    private Polygon polygon;
    private double edgeWidth;
    private Color backgroundColor;
    private Color outputColor;
    private Text cellText;

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

        setPickOnBounds(false);

        cellText = new Text(cellValue);
        cellText.setPickOnBounds(false);
        cellText.setStyle(
                "-fx-font-size: 18;" +
                        "-fx-font-family:Arial;" +
                        "-fx-font-weight:700");
        polygon.setStroke(Color.WHITE);
        polygon.setStrokeWidth(7.5);
        polygon.setFill(backgroundColor);
        polygon.setCursor(Cursor.HAND);
        getChildren().addAll(polygon, cellText);
    }

    public Color getOutputColor() {
        return outputColor;
    }

    public String getCellValue() {
        return cellValue;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setCellValue(String val) {
        this.cellValue = val;
        cellText.setText(cellValue);
    }


}
