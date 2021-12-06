package project.maps;

import project.*;
import project.orientation.Vector2d;
import project.elements.Animal;
import project.elements.Grass;
import project.elements.IMapElement;
import project.visualization.MapVisualizer;

import java.util.LinkedHashMap;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver
{
    protected MapVisualizer drawer;
    protected Vector2d mapLeftSize;
    protected Vector2d mapRightSize;
    protected LinkedHashMap<Vector2d, IMapElement> elemPosition = new LinkedHashMap<>();
    protected void setDrawer(MapVisualizer drawer) {
        this.drawer = drawer;
    }

    public AbstractWorldMap(Vector2d mapLeftSize, Vector2d mapRightSize)
    {
        this.mapLeftSize = mapLeftSize;
        this.mapRightSize = mapRightSize;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        IMapElement element = elemPosition.get(oldPosition);
        if (element instanceof Animal)
        {

            elemPosition.remove(oldPosition);
            elemPosition.put(newPosition,(Animal) element);

        }
    }

    public String toString()
    {
        return drawer.draw(this.mapLeftSize, this.mapRightSize);
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException
    {
        if (!(objectAt(animal.getPosition()) instanceof Animal))
        {
            addElement(animal);
            return true;
        }
        else
        {
            throw new IllegalArgumentException("Position at " + animal.getPosition()  +  " is occupied");
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return elemPosition.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position)
    {
        if (elemPosition.containsKey(position))
        {
            if (elemPosition.get(position) instanceof  Animal) return (Animal) elemPosition.get(position);
            if (elemPosition.get(position) instanceof Grass) return (Grass) elemPosition.get(position);
        }
        return null;
    }

    public void addElement(IMapElement element)
    {
        elemPosition.put(element.getPosition(), element);
        element.addObserver(this);
    }

    public void grassEaten(Vector2d position)
    {
        Grass grass = (Grass) elemPosition.get(position);
        grass.removeObserver(this);
        elemPosition.remove(position);

    }

    public Vector2d getEmptyLocation()
    {
        Vector2d position = new Vector2d(Generate.RandInt(this.mapLeftSize.getX(), this.mapRightSize.getX()),
                                         Generate.RandInt(this.mapLeftSize.getY(), this.mapRightSize.getY()));
        while (this.elemPosition.containsKey(position))
        {
            position = new Vector2d(Generate.RandInt(this.mapLeftSize.getX(), this.mapRightSize.getX()),
                                             Generate.RandInt(this.mapLeftSize.getY(), this.mapRightSize.getY()));
        }
        return position;
    }
}
