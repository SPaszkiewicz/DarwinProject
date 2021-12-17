package project.maps;

import project.Generate;
import project.orientation.Vector2d;

import java.util.ArrayList;

public class Steppe implements IRegion
{
    private final Vector2d jungleLeftCorner;
    private final Vector2d jungleRightCorner;
    private final Vector2d leftCorner;
    private final Vector2d rightCorner;
    private IWorldMap map;

    public Steppe(Vector2d jungleLeftCorner, Vector2d jungleRightCorner, IWorldMap map, Vector2d leftCorner, Vector2d rightCorner)
    {
        this.jungleLeftCorner = jungleLeftCorner;
        this.jungleRightCorner = jungleRightCorner;
        this.rightCorner = rightCorner;
        this.leftCorner = leftCorner;
        this.map = map;
    }

    @Override
    public void setMap(IWorldMap map)
    {
        this.map = map;
    }

    @Override
    public boolean inRegion(Vector2d position)
    {
        return  !(position.follows(jungleLeftCorner) && position.precedes(jungleRightCorner));
    }

    @Override
    public Vector2d getRandomPosition() {
        ArrayList<Vector2d> consideredVectors = new ArrayList<>();
        for (int i = leftCorner.getX(); i < rightCorner.getX()+1; i += 1)
        {
            for (int j = leftCorner.getY(); j < rightCorner.getY()+1; j += 1)
            {
                if (inRegion(new Vector2d(i, j)) && !this.map.isOccupied(new Vector2d(i, j))) {
                    consideredVectors.add(new Vector2d(i, j));
                }
            }
        }
        if (consideredVectors.size() > 0) return consideredVectors.get(Generate.randInt(0, consideredVectors.size()-1));
        else return new Vector2d(-1, -1);
    }
}
