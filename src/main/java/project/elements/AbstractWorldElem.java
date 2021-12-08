package project.elements;

import project.orientation.Vector2d;
import project.maps.IWorldMap;
import project.IPositionChangeObserver;
import java.util.LinkedList;

abstract class AbstractWorldElem implements IMapElement
{
    protected Vector2d position;
    protected IWorldMap map;
    protected final LinkedList<IPositionChangeObserver> observers = new LinkedList<IPositionChangeObserver>();
    public AbstractWorldElem(Vector2d position, IWorldMap map)
    {
        this.position = position;
        this.map = map;
    }

    public Vector2d getPosition() {
        return position;
    }

    public IWorldMap getMap() {
        return map;
    }

    public void setMap(IWorldMap map) {
        this.map = map;
    }

    public void addObserver(IPositionChangeObserver observer)
    {
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer)
    {
        this.observers.remove(observer);
    }


}