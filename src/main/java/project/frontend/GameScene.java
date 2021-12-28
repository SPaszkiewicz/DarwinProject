package project.frontend;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import project.elements.Animal;
import project.maps.IWorldMap;
import project.maps.LeftMap;
import project.maps.RightMap;
import project.orientation.Vector2d;
import project.simulation.CsvWriter;
import project.simulation.SimulationEngine;
import project.simulation.Statistics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class GameScene implements IMapObserver
{
    private final IWorldMap map;
    private final MapGridPane visualization;
    private final SimulationEngine engine;
    private final Thread threadEngine;
    private final Button button = new Button("START");
    private VBox rightPanel;
    private final PlotContainer plots = new PlotContainer();
    private final HBox container;
    private boolean wasRunned = false;
    private final SelectedAnimalGui tracker = new SelectedAnimalGui();
    private int descendants;
    private final CsvWriter data;

    private final Label amnGrass = new Label("Amount of grass: 0");
    private final Label amnAnimals = new Label("Amount of animals: 0");
    private final Label avgEnergy = new Label("Average Energy: 0");
    private final Label avgKids = new Label("Average amount of kids: 0");
    private final Label expLife = new Label("Life expectancy: 0");
    private final Label dominant = new Label("Dominant: ");
    private final Label evolutionLives = new Label("Magic lives: 3");


    public GameScene(Options options, boolean mapType, boolean evolutionType) {
        // FOR STATISTICS
        Label title = new Label("Overall informations:");
        title.setStyle("-fx-font-weight: bold");
        if (mapType) {
            this.map = new RightMap(new Vector2d(0, 0),
                    new Vector2d(options.getWidth(),
                            options.getHeigh()),
                    options.getStartEnergy(),
                    options.getMoveEnergy(),
                    options.getPlantEnergy(),
                    options.getJungleRatio(),
                    options.getRotateEnergy());
            this.data = new CsvWriter("Statistics/RightStats.csv");
        }
        else
        {
            this.map = new LeftMap(new Vector2d(0, 0),
                    new Vector2d(options.getWidth(),
                            options.getHeigh()),
                    options.getStartEnergy(),
                    options.getMoveEnergy(),
                    options.getPlantEnergy(),
                    options.getJungleRatio(),
                    options.getRotateEnergy());
            this.data = new CsvWriter("Statistics/LeftStats.csv");
        }


        switchStartButton(button);
        Label distance1 = new Label("                                                                                                                                   ");
        Label distance2 = new Label("                                                                                                                                   ");
        this.visualization = new MapGridPane(this.map, this);
        this.engine = new SimulationEngine(this.map, options.getAmountOfAnimals(), options.getStartEnergy(), evolutionType);
        this.engine.addObserver(this);
        this.threadEngine = new Thread(engine);
        try {
            if (evolutionType) this.rightPanel = new VBox(visualization.updateMap(), distance1, button, distance2, title, evolutionLives, amnAnimals, amnGrass, avgEnergy, avgKids, expLife, dominant, tracker.getInformation());
            else this.rightPanel = new VBox(visualization.updateMap(), distance1, button, distance2, title, amnAnimals, amnGrass, avgEnergy, avgKids, expLife, dominant, tracker.getInformation());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        container = new HBox(plots.getContainer(), rightPanel);
        container.setAlignment(Pos.CENTER);
        container.setBackground(new Background(new BackgroundFill(Color.web("FFE6BC"), CornerRadii.EMPTY, Insets.EMPTY)));
        tracker.getInformation().setBackground(new Background(new BackgroundFill(Color.web("E4CDA7"), CornerRadii.EMPTY, Insets.EMPTY)));
    }


    public void switchStartButton(Button button) {
        button.setOnAction(action ->
        {
            if (Objects.equals(button.getText(), "START")) {
                if (!this.wasRunned) {
                    this.wasRunned = true;
                    this.threadEngine.start();
                } else {
                    this.engine.start();
                }
                button.setText("STOP");
            } else {
                try {
                    this.data.makeStatistics();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                this.engine.stop();
                button.setText("START");
            }
        });


    }

    public void closeThreads()
    {
        engine.setExit(false);
    }

    public IWorldMap getMap() {
        return map;
    }


    public HBox getContainer() {
        return container;
    }

    @Override
    public void mapChanged(IWorldMap map, Statistics statistic) {
        Platform.runLater(() ->
        {
            //OVERALL INFORMATION
            try
            {
                this.data.gameStatistics(statistic, engine.getDay());
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            this.updateStatistics(statistic);
            this.visualization.clearMap();
            try {
                this.visualization.updateMap();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //TRACKED INFORMATION
            if (tracker.animal != null)
            {
                if (!tracker.isDead)
                {
                    if (tracker.animal.getEnergy() <= 0) tracker.updateDeath(engine.getDay());
                }
                this.descendants += statistic.descendants;
                tracker.updateInformations(this.descendants);
            }
            //PLOT INFORMATION
            this.plots.getAnimalsPlot().updateData(engine.getDay(), statistic.amountOfAnimals);
            this.plots.getEnergyPlot().updateData(engine.getDay(), statistic.averageEnergyPerAnimal);
            this.plots.getGrassPlot().updateData(engine.getDay(), statistic.amountOfGrass);
            this.plots.getKidsPlot().updateData(engine.getDay(), statistic.averageKidsPerAnimal);
            this.plots.getLifePlot().updateData(engine.getDay(), statistic.lifeExpectancy);
        });
    }

    @Override
    public void setTraction(Animal animal)
    {
        if (this.button.getText().equals("START") && tracker.animal != animal)
        {
            this.engine.clearMarkFromAnimals();
            tracker.newAnimal(animal);
            this.descendants = 0;
        }
    }

    public void updateStatistics(Statistics statistics) {
        if (this.engine.getTries() == 0) this.evolutionLives.setTextFill(Color.RED);
        this.evolutionLives.setText("Magic lives: " + this.engine.getTries());
        this.dominant.setText("Dominant: " + statistics.dominant);
        this.amnGrass.setText("Amount of grass: " + statistics.amountOfGrass);
        this.amnAnimals.setText("Amount of animals: " + statistics.amountOfAnimals);
        this.avgEnergy.setText("Average Energy: " + statistics.averageEnergyPerAnimal);
        this.avgKids.setText("Average amount of kids: " + statistics.averageKidsPerAnimal);
        this.expLife.setText("Life expectancy: " + statistics.lifeExpectancy);
    }
}
