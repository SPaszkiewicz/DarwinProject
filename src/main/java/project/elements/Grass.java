package project.elements;

import project.orientation.Vector2d;
import project.maps.IWorldMap;

public class Grass extends AbstractWorldElem
{

    public Grass(Vector2d position, IWorldMap map) {
        super(position, map);
    }
    @Override
    public String toString()
    {
        return "*";
    }
}
