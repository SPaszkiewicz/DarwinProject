package project.elements;

import project.maps.IPositionChangeObserver;
import project.orientation.Vector2d;

public interface IMapElement
{
    Vector2d getPosition();

    void addObserver(IPositionChangeObserver observer);

}
