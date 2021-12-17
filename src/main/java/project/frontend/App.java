package project.frontend;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.elements.Grass;
import project.frontend.Options;
import project.frontend.ValidationTextField;
import project.maps.IWorldMap;
import project.maps.LeftMap;
import project.orientation.Vector2d;


public class App  extends Application
{
    private Options options;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(guiStart());
        primaryStage.show();
//        primaryStage.setScene(gameStage());
//        primaryStage.show();
    }

    public Scene guiStart() throws Exception
    {
        FlowPane flowpane = new FlowPane();
        flowpane.setHgap(10);
        flowpane.setVgap(10);
        flowpane.setAlignment(Pos.CENTER);


        Label RecordXSize = new Label("Width of the map:");
        TextField xSizeOfMap = new TextField();
        ValidationTextField.applyIntegerFormatToField(xSizeOfMap);
        Label validationInputX = ValidationTextField.createValidationLabel();
        VBox xSizeBox = new VBox(RecordXSize, xSizeOfMap,validationInputX);

        Label RecordYSize = new Label("Heigh of the map:");
        TextField ySizeOfMap = new TextField();
        ValidationTextField.applyIntegerFormatToField(ySizeOfMap);
        Label validationInputY = ValidationTextField.createValidationLabel();
        VBox ySizeBox = new VBox(RecordYSize, ySizeOfMap, validationInputY);

        Label RecordStartEnergy = new Label("Animal start energy:");
        TextField startEnergy = new TextField();
        ValidationTextField.applyIntegerFormatToField(startEnergy);
        Label validationInputStart = ValidationTextField.createValidationLabel();
        VBox startEnergyBox = new VBox(RecordStartEnergy, startEnergy, validationInputStart);

        Label RecordMoveEnergy = new Label("Animal move energy:");
        TextField moveEnergy = new TextField();
        ValidationTextField.applyIntegerFormatToField(moveEnergy);
        Label validationInputMove = ValidationTextField.createValidationLabel();
        VBox moveEnergyBox = new VBox(RecordMoveEnergy, moveEnergy, validationInputMove);

        Label RecordRotateEnergy = new Label("Animal rotate energy:");
        TextField rotateEnergy = new TextField();
        ValidationTextField.applyIntegerFormatToField(rotateEnergy);
        Label validationInputRotate = ValidationTextField.createValidationLabel();
        VBox rotateEnergyBox = new VBox(RecordRotateEnergy, rotateEnergy, validationInputRotate);

        Label RecordPlantEnergy = new Label("Energy from plants:");
        TextField plantEnergy = new TextField();
        ValidationTextField.applyIntegerFormatToField(plantEnergy);
        Label validationInputPlants = ValidationTextField.createValidationLabel();
        VBox plantEnergyBox = new VBox(RecordPlantEnergy, plantEnergy, validationInputPlants);

        Label RecordJungleRatio = new Label("Jungle ratio:");
        TextField jungleRatio = new TextField();
        ValidationTextField.applyFloatFormatToField(jungleRatio);
        Label validationInputJungle = ValidationTextField.createValidationLabel();
        VBox jungleRatioBox= new VBox(RecordJungleRatio, jungleRatio, validationInputJungle);

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

            if (!(emptyValidation1 || emptyValidation2 || emptyValidation3 || emptyValidation4 || emptyValidation5 || emptyValidation6 || emptyValidation7))
            {
                this.options = new Options
                        (
                                Integer.parseInt(xSizeOfMap.getText()),
                                Integer.parseInt(ySizeOfMap.getText()),
                                Integer.parseInt(startEnergy.getText()),
                                Integer.parseInt(moveEnergy.getText()),
                                Integer.parseInt(rotateEnergy.getText()),
                                Integer.parseInt(plantEnergy.getText()),
                                Float.parseFloat(jungleRatio.getText())
                        );
                System.out.println(options);
            }
        });

        flowpane.getChildren().add(xSizeBox);
        flowpane.getChildren().add(ySizeBox);
        flowpane.getChildren().add(startEnergyBox);
        flowpane.getChildren().add(moveEnergyBox);
        flowpane.getChildren().add(rotateEnergyBox);
        flowpane.getChildren().add(plantEnergyBox);
        flowpane.getChildren().add(jungleRatioBox);
        flowpane.getChildren().add(startButton);
        return new Scene(flowpane, 350, 300);
    }

    public Scene gameStage() throws Exception
    {
        IWorldMap map = new LeftMap(new Vector2d(0, 0), new Vector2d(10, 10), 7, 1, 40, (float) 0.3, 1);
        MapGridPane test = new MapGridPane(map);
        map.addGrassToSteppe();
        map.addGrassToJungle();
        return new Scene(test.updateMap(), 400, 400);
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
