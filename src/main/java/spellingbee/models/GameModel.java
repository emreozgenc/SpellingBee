package spellingbee.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameModel {
    private IntegerProperty pointProperty = new SimpleIntegerProperty();
    private StringProperty wordProperty = new SimpleStringProperty();
    private StringProperty errorProperty = new SimpleStringProperty();

    public void setPointValue(int value) {
        pointProperty.setValue(value);
    }

    public int getPointValue() {
        return pointProperty.getValue();
    }

    public IntegerProperty getIntegerProperty() {
        return pointProperty;
    }

    public void setWordValue(String word) {
        wordProperty.setValue(word);
    }

    public String getWordValue() {
        return wordProperty.getValue();
    }

    public StringProperty getWordProperty() {
        return wordProperty;
    }

    public void setErrorValue(String error) {
        errorProperty.setValue(error);
    }

    public String getErrorValue() {
        return errorProperty.getValue();
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }
}
