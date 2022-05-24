package spellingbee.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MenuModel {
    private StringProperty lettersProperty = new SimpleStringProperty();
    private StringProperty errorProperty = new SimpleStringProperty();

    public void setLettersPropertyValue(String val) {
        lettersProperty.setValue(val);
    }

    public String getLettersPropertyValue() {
        return lettersProperty.getValue();
    }

    public StringProperty getLettersProperty() {
        return lettersProperty;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public String getErrorPropertyValue() {
        return errorProperty.getValue();
    }

    public void setErrorPropertyValue(String val) {
        errorProperty.setValue(val);
    }
}
