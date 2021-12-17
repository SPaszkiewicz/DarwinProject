package project.frontend;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class ValidationTextField
{

    public static void applyIntegerFormatToField(TextField field) {
        field.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        field.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { // when focus lost
                if (!field.getText().matches("[1-5](\\.[0-9]{1,2}){0,1}|6(\\.0{1,2}){0,1}")) {
                    // when it not matches the pattern (1.0 - 6.0)
                    // set the textField empty
                    field.setText("");
                }
            }
        });
    }

    public static void applyFloatFormatToField(TextField field) {
        field.setTextFormatter(new TextFormatter<>(new FloatStringConverter()));
    }

    public static boolean isTextFieldEmpty(TextField field, Label validationCommunicate)
    {
        if (field.getText().isEmpty())
        {
            validationCommunicate.setText("Data is required");
            return true;
        }
        else
        {
            validationCommunicate.setText("");
            return false;
        }
    }

    public static Label createValidationLabel()
    {
        Label label = new Label("");
        label.setTextFill(Color.RED);
        label.setFont(new Font("Arial", 11));
        return label;
    }

}
