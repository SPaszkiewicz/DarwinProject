package project;

import project.maps.IWorldMap;
import project.orientation.Vector2d;
import project.simulation.Statistics;

import java.io.FileNotFoundException;

public interface IMapObserver
{
    void mapChanged(IWorldMap map, Statistics statistic) throws FileNotFoundException;
}
