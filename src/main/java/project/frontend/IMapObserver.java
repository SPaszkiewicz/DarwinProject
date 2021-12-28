package project.frontend;

import project.elements.Animal;
import project.maps.IWorldMap;
import project.simulation.Statistics;

import java.io.FileNotFoundException;

public interface IMapObserver
{
    void mapChanged(IWorldMap map, Statistics statistic) throws FileNotFoundException;

    void setTraction(Animal animal);
}
