package project.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartScene
{
    private Options options;
    private final Scene mainScene;
    private final Stage stage;

    public StartScene(Stage stage, App app)
    {
        this.stage = stage;
        FlowPane flowpane = new FlowPane();
        flowpane.setHgap(20);
        flowpane.setVgap(20);
        flowpane.setAlignment(Pos.CENTER);
        flowpane.setBackground(new Background(new BackgroundFill(Color.web("FFE6BC"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label RecordXSize = new Label("Width of the map:");
        TextField xSizeOfMap = new TextField();
        Label validationInputX = ValidationTextField.createValidationLabel();
        ValidationTextField.applyIntegerFormatToSize(xSizeOfMap, validationInputX);
        VBox xSizeBox = new VBox(RecordXSize, xSizeOfMap,validationInputX);

        Label RecordYSize = new Label("Heigh of the map:");
        TextField ySizeOfMap = new TextField();
        Label validationInputY = ValidationTextField.createValidationLabel();
        ValidationTextField.applyIntegerFormatToSize(ySizeOfMap, validationInputY);
        VBox ySizeBox = new VBox(RecordYSize, ySizeOfMap, validationInputY);

        Label RecordStartEnergy = new Label("Animal start energy:");
        TextField startEnergy = new TextField();
        Label validationInputStart = ValidationTextField.createValidationLabel();
        ValidationTextField.applyIntegerFormatToField(startEnergy, validationInputStart);
        VBox startEnergyBox = new VBox(RecordStartEnergy, startEnergy, validationInputStart);

        Label RecordMoveEnergy = new Label("Animal move energy:");
        TextField moveEnergy = new TextField();
        Label validationInputMove = ValidationTextField.createValidationLabel();
        ValidationTextField.applyIntegerFormatToField(moveEnergy, validationInputMove);
        VBox moveEnergyBox = new VBox(RecordMoveEnergy, moveEnergy, validationInputMove);

        Label RecordRotateEnergy = new Label("Animal rotate energy:");
        TextField rotateEnergy = new TextField();
        Label validationInputRotate = ValidationTextField.createValidationLabel();
        ValidationTextField.applyIntegerFormatToField(rotateEnergy, validationInputRotate);
        VBox rotateEnergyBox = new VBox(RecordRotateEnergy, rotateEnergy, validationInputRotate);

        Label RecordPlantEnergy = new Label("Energy from plants:");
        TextField plantEnergy = new TextField();
        Label validationInputPlants = ValidationTextField.createValidationLabel();
        ValidationTextField.applyIntegerFormatToField(plantEnergy, validationInputPlants);
        VBox plantEnergyBox = new VBox(RecordPlantEnergy, plantEnergy, validationInputPlants);

        Label RecordJungleRatio = new Label("Jungle ratio:");
        TextField jungleRatio = new TextField();
        Label validationInputJungle = ValidationTextField.createValidationLabel();
        ValidationTextField.applyFloatFormatToField(jungleRatio, validationInputJungle);
        VBox jungleRatioBox= new VBox(RecordJungleRatio, jungleRatio, validationInputJungle);

        Label RecordAmountOfAnimals = new Label("Amount of animals:");
        TextField amountOfAnimals = new TextField();
        Label validationInputAnimals = ValidationTextField.createValidationLabel();
        ValidationTextField.applyIntegerFormatToField(amountOfAnimals, validationInputAnimals);
        VBox amountOfAnimalsBox= new VBox(RecordAmountOfAnimals, amountOfAnimals, validationInputAnimals);

        Label leftDescription = new Label("Left map");
        ComboBox<String> leftType = new ComboBox<>();
        leftType.getItems().add("Normal");
        leftType.getItems().add("Magic");
        leftType.getSelectionModel().selectFirst();
        VBox leftBox = new VBox(leftDescription, leftType);

        Label rightDescription = new Label("Right map");
        ComboBox<String> rightType = new ComboBox<>();
        rightType.getItems().add("Normal");
        rightType.getItems().add("Magic");
        rightType.getSelectionModel().selectFirst();
        VBox rightBox = new VBox(rightDescription, rightType);

        Button startButton = new Button("START");
        startButton.setOnAction(action ->
        {
            boolean emptyValidation1 = ValidationTextField.isTextFieldEmpty(xSizeOfMap, validationInputX);
            boolean emptyValidation2 = ValidationTextField.isTextFieldEmpty(ySizeOfMap, validationInputY);
            boolean emptyValidation3 = ValidationTextField.isTextFieldEmpty(startEnergy, validationInputStart);
            boolean emptyValidation4 = ValidationTextField.isTextFieldEmpty(moveEnergy, validationInputMove);
            boolean emptyValidation5 = ValidationTextField.isTextFieldEmpty(rotateEnergy, validationInputRotate);
            boolean emptyValidation6 = ValidationTextField.isTextFieldEmpty(plantEnergy, validationInputPlants);
            boolean emptyValidation7 = ValidationTextField.isTextFieldEmpty(jungleRatio, validationInputJungle);
            boolean emptyValidation8 = ValidationTextField.isTextFieldEmpty(amountOfAnimals, validationInputAnimals);

            if (!(emptyValidation1 || emptyValidation2 || emptyValidation3 ||
                    emptyValidation4 || emptyValidation5 || emptyValidation6 ||
                    emptyValidation7 || emptyValidation8))
            {
                this.options = new Options
                        (
                                Integer.parseInt(xSizeOfMap.getText())-1,
                                Integer.parseInt(ySizeOfMap.getText())-1,
                                Integer.parseInt(startEnergy.getText()),
                                Integer.parseInt(moveEnergy.getText()),
                                Integer.parseInt(rotateEnergy.getText()),
                                Integer.parseInt(plantEnergy.getText()),
                                Float.parseFloat(jungleRatio.getText()),
                                Integer.parseInt(amountOfAnimals.getText())
                        );
                boolean left;
                boolean right;
                left = leftType.getValue().equals("Magic");
                right = rightType.getValue().equals("Magic");
                app.gameScene(this.stage, this.options, left, right);
            }

        });

        flowpane.getChildren().add(xSizeBox);
        flowpane.getChildren().add(ySizeBox);
        flowpane.getChildren().add(startEnergyBox);
        flowpane.getChildren().add(moveEnergyBox);
        flowpane.getChildren().add(rotateEnergyBox);
        flowpane.getChildren().add(plantEnergyBox);
        flowpane.getChildren().add(jungleRatioBox);
        flowpane.getChildren().add(amountOfAnimalsBox);
        flowpane.getChildren().add(leftBox);
        flowpane.getChildren().add(rightBox);
        flowpane.getChildren().add(startButton);
        try {
            Image image = new javafx.scene.image.Image(new FileInputStream("src/main/resources/world.png"), 100, 100, true, true);
            ImageView view = new ImageView(image);
            view.setFitWidth(100);
            view.setFitHeight(100);
            flowpane.getChildren().add(view);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.mainScene = new Scene(flowpane, 370, 420);
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
