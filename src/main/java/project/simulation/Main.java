package project.simulation;


import project.maps.RightMap;
import project.orientation.Vector2d;
import project.maps.LeftMap;

public class Main
{
    static public void main(String[] args) throws InterruptedException
    {
        LeftMap map = new LeftMap(new Vector2d(0, 0), new Vector2d(8, 8), 7, 1, 40, (float) 0.3, 1);
        SimulationEngine engine = new SimulationEngine(map, 10, 7);
        System.out.println("Begin");
        engine.run();
        System.out.println("End");
    }
}
