package project.simulation;

import project.Generate;
import project.elements.Animal;
import project.maps.RightMap;
import project.orientation.MapDirection;
import project.orientation.Vector2d;
import project.maps.LeftMap;

public class Main
{
    static public void main(String[] args)
    {
        RightMap map = new RightMap(new Vector2d(0, 0), new Vector2d(10, 5));
        SimulationEngine engine = new SimulationEngine(map, 5, 7, 15);
        System.out.println("Begin");
        engine.run();
        System.out.println("End");
    }
}
