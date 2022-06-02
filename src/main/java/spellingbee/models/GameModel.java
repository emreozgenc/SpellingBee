package spellingbee.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameModel {
    private final IntegerProperty pointProperty = new SimpleIntegerProperty();
    private final StringProperty wordProperty = new SimpleStringProperty();
    private final StringProperty statusProperty = new SimpleStringProperty();
    private final StringProperty resultWordProperty = new SimpleStringProperty();
    private final IntegerProperty currentPointProperty = new SimpleIntegerProperty();
    private final IntegerProperty maximumPointProperty = new SimpleIntegerProperty();

    public final String letters;

    public GameModel(String letters) {
        this.letters = letters;
    }

    public void setPointPropertyValue(int val) {
        pointProperty.setValue(val);
    }

    public int getPointPropertyValue() {
        return pointProperty.getValue();
    }

    public IntegerProperty getPointProperty() {
        return pointProperty;
    }

    public void setWordPropertyValue(String val) {
        wordProperty.setValue(val);
    }

    public String getWordPropertyValue() {
        return wordProperty.getValue();
    }

    public StringProperty getWordProperty() {
        return wordProperty;
    }

    public void setStatusPropertyValue(String val) {
        statusProperty.setValue(val);
    }

    public String getStatusPropertyValue() {
        return statusProperty.getValue();
    }

    public StringProperty getStatusProperty() {
        return statusProperty;
    }

    public void setResultWordPropertyValue(String val) {
        resultWordProperty.setValue(val);
    }

    public String getResultWordPropertyValue() {
        return resultWordProperty.getValue();
    }

    public StringProperty getResultWordProperty() {
        return resultWordProperty;
    }

    public void setCurrentPointPropertyValue(int val) {
        currentPointProperty.setValue(val);
    }

    public int getCurrentPointPropertyValue() {
        return currentPointProperty.getValue();
    }

    public IntegerProperty getCurrentPointProperty() {
        return currentPointProperty;
    }

    public void setMaximumPointPropertyValue(int val) {
        maximumPointProperty.setValue(val);
    }

    public int getMaximumPointPropertyValue() {
        return maximumPointProperty.getValue();
    }

    public IntegerProperty getMaximumPointProperty() {
        return maximumPointProperty;
    }

}
