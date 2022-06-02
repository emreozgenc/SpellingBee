package spellingbee.components;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BeehiveCell extends StackPane {
    private String cellValue;
    private Polygon polygon;
    private double edgeWidth;
    private Color backgroundColor;
    private Color outputColor;
    private Text cellText;
    private ScaleTransition shrinkTransition;
    private ScaleTransition growTransition;

    private FadeTransition fadeInTransition;
    private FadeTransition fadeOutTransition;
    private SequentialTransition clickAnimation;
    private SequentialTransition shuffleAnimation;
    private boolean clickAnimationFlag = true;

    public BeehiveCell(String cellValue, double edgeWidth, Color backgroundColor, Color outputColor) {
        this.cellValue = cellValue;
        this.edgeWidth = edgeWidth;
        this.backgroundColor = backgroundColor;
        this.outputColor = outputColor;

        init();
        initAnimations();
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
        cellText.getStyleClass().add("cell-text");

        polygon.setStroke(Color.WHITE);
        polygon.setStrokeWidth(7.5);
        polygon.setFill(backgroundColor);
        polygon.setCursor(Cursor.HAND);
        getChildren().addAll(polygon, cellText);
    }

    private void initAnimations() {
        fadeInTransition = new FadeTransition(Duration.millis(300), cellText);
        fadeInTransition.setToValue(1);

        fadeOutTransition = new FadeTransition(Duration.millis(10), cellText);
        fadeOutTransition.setToValue(0);

        shuffleAnimation = new SequentialTransition(fadeOutTransition, fadeInTransition);

        shrinkTransition = new ScaleTransition(Duration.millis(100), polygon);
        shrinkTransition.setToX(0.8);
        shrinkTransition.setToY(0.8);

        growTransition = new ScaleTransition(Duration.millis(100), polygon);
        growTransition.setToX(1);
        growTransition.setToY(1);

        clickAnimation = new SequentialTransition(shrinkTransition, growTransition);
        clickAnimation.setOnFinished(e -> clickAnimationFlag = true);
    }

    public void playClickAnimation() {
        if (clickAnimationFlag) {
            clickAnimationFlag = false;
            clickAnimation.play();
        }
    }

    public void playShuffleAnimation() {
        shuffleAnimation.play();
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

    public Polygon getPolygon() {
        return polygon;
    }


}
