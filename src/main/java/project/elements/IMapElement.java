package project.elements;

import project.IPositionChangeObserver;
import project.maps.IWorldMap;
import project.orientation.Vector2d;

public interface IMapElement
{
    Vector2d getPosition();

    void addObserver(IPositionChangeObserver observer);

    void removeObserver(IPositionChangeObserver observer);
}
