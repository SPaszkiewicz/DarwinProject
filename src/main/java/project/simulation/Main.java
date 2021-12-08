package project.simulation;


import project.maps.RightMap;
import project.orientation.Vector2d;
import project.maps.LeftMap;

public class Main
{
    static public void main(String[] args)
    {
        LeftMap map = new LeftMap(new Vector2d(0, 0), new Vector2d(5, 5), 7, 1, 40, 0, 1, 5);
        SimulationEngine engine = new SimulationEngine(map, 10, 7, 5);
        System.out.println("Begin");
        engine.run();
        System.out.println("End");
    }
}
