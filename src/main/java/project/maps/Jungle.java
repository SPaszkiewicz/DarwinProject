package project.maps;

import project.Generate;
import project.orientation.Vector2d;

import java.util.ArrayList;

public class Jungle implements IRegion {
    private final Vector2d leftCorner;
    private final Vector2d rightCorner;
    private IWorldMap map;

    public Jungle(Vector2d leftCorner, Vector2d rightCorner, IWorldMap map) {
        this.leftCorner = leftCorner;
        this.rightCorner = rightCorner;
        this.map = map;
    }

    @Override
    public void setMap(IWorldMap map) {
        this.map = map;
    }

    @Override
    public boolean inRegion(Vector2d position) {
        return position.follows(leftCorner) && position.precedes(rightCorner);
    }

    @Override
    public Vector2d getRandomPosition() {
        ArrayList<Vector2d> consideredVectors = new ArrayList<>();
        for (int i = leftCorner.getX(); i < rightCorner.getX() + 1; i += 1) {
            for (int j = leftCorner.getY(); j < rightCorner.getY() + 1; j += 1) {
                if (this.map.isOccupied(new Vector2d(i, j))) consideredVectors.add(new Vector2d(i, j));
            }
        }
        if (consideredVectors.size() > 0)
            return consideredVectors.get(Generate.randInt(0, consideredVectors.size() - 1));
        else return new Vector2d(-1, -1);
    }
}
