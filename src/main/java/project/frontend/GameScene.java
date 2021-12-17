package project.frontend;

import javafx.scene.layout.GridPane;
import project.IMapObserver;
import project.maps.IWorldMap;
import project.maps.LeftMap;
import project.maps.RightMap;
import project.orientation.Vector2d;
import project.simulation.IEngine;
import project.simulation.SimulationEngine;
import project.simulation.Statistics;

import java.io.FileNotFoundException;

public class GameScene implements IMapObserver
{
    private final SimulationEngine engineLeft;
    private final SimulationEngine engineRight;
    private  final MapGridPane leftMap;
    private  final MapGridPane rightMap;
    public GameScene(Options options)
    {
        this.engineLeft = new SimulationEngine(
                new LeftMap(new Vector2d(0, 0), new Vector2d(options.getWidth(), options.getHeigh()), options.getStartEnergy(),
                        options.getMoveEnergy(), options.getPlantEnergy(), options.getJungleRatio(), options.getRotateEnergy())
        , 5, options.getStartEnergy());
        this.engineRight = new SimulationEngine(
                new RightMap(new Vector2d(0, 0), new Vector2d(options.getWidth(), options.getHeigh()), options.getStartEnergy(),
                        options.getMoveEnergy(), options.getPlantEnergy(), options.getJungleRatio(), options.getRotateEnergy())
                , 5, options.getStartEnergy());

        this.leftMap = new MapGridPane(engineLeft.getMap());
        this.rightMap = new MapGridPane(engineRight.getMap());
    }


    @Override
    public void mapChanged(IWorldMap map, Statistics statistic) throws FileNotFoundException
    {
        this.leftMap.updateMap();
    }
}
