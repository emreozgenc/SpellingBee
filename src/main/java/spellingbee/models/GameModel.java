package spellingbee.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameModel {
    private IntegerProperty pointProperty = new SimpleIntegerProperty();
    private StringProperty wordProperty = new SimpleStringProperty();
    private StringProperty errorProperty = new SimpleStringProperty();

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

    public void setErrorPropertyValue(String val) {
        errorProperty.setValue(val);
    }

    public String getErrorPropertyValue() {
        return errorProperty.getValue();
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }
}
