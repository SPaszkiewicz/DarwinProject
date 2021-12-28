package project.frontend;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ValidationTextField
{

    public static void applyIntegerFormatToField(TextField field, Label validation) {
        field.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue)
            {
                if (!field.getText().matches("\\d*"))
                {
                    if (field.getText().matches("[-][1-9]{1,100}|[0]")) {
                        field.setText("");
                        validation.setText("Give number bigger than 0");
                    }
                    else {
                        field.setText("");
                        validation.setText("Give only numbers");
                    }
                }
                else if (!field.getText().matches("[1-9]{1,1}[0-9]{0,4}")) {
                    field.setText("");
                    validation.setText("Give number under 10000");
                }
                else
                {
                    validation.setText("");
                }
            }
        });
    }

    public static void applyIntegerFormatToSize(TextField field, Label validation) {
        field.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue)
            {
                if (!field.getText().matches("\\d*"))
                {
                    if (field.getText().matches("[-][1-9]{1,100}")) {
                        field.setText("");
                        validation.setText("Give number bigger than 0");
                    }
                    else {
                        field.setText("");
                        validation.setText("Give only numbers");
                    }
                }
                else if (field.getText().equals("0"))
                {
                    field.setText("");
                    validation.setText("Give number bigger than 0");
                }
                else if (field.getText().matches("([1-2]{1,1}[0-9])|[1-9]|30")) {
                    validation.setText("");
                }
                else
                {
                    field.setText("");
                    validation.setText("Give number under or equal 30");
                }
            }
        });
    }

    public static void applyFloatFormatToField(TextField field, Label validation) {
        field.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue)
            {
                if (field.getText().matches("[0][.]([1-9]{3,100})"))
                {
                    field.setText(field.getText().substring(0, 4));
                }
                else if (field.getText().matches("[0][.]([1-9]{0,2})|[1]"))
                {
                    validation.setText("");
                }
                else if (field.getText().matches("([1-9]{1,100})[.]([1-9]{0,100})"))
                {
                    field.setText("");
                    validation.setText("Give number not bigger than 1");
                }
                else if (!field.getText().matches("\\d*"))
                {
                    field.setText("");
                    validation.setText("Give only numbers");
                }
                else if (field.getText().matches("[-][1-9]{1,100}|[0]")) {
                    field.setText("");
                    validation.setText("Give number bigger than 0");
                }
                else
                {
                    field.setText("");
                    validation.setText("Give number not bigger than 1");
                }
            }
        });
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
