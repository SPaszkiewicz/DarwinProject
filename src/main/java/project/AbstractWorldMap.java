package project;

import java.util.LinkedHashMap;

abstract class AbstractWorldMap implements  IWorldMap
{
    protected MapVisualizer drawer;
    protected Vector2d mapLeftSize = new Vector2d(0, 0);
    protected Vector2d mapRightSize;
    protected LinkedHashMap<Vector2d, IMapElement> elemLocation = new LinkedHashMap<>();
    protected void setDrawer(MapVisualizer drawer) {
        this.drawer = drawer;
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
            elemLocation.put(animal.getPosition(), animal);
            return true;
        }
        else
        {
            throw new IllegalArgumentException("Position at " + animal.getLocation()  +  " is occupied");
        }
    }


    @Override
    public boolean canMoveTo(Vector2d position)
    {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public Vector2d WhereToMoveTo(Vector2d position)
    {
        //TODO
        return new Vector2d(0, 0);
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return elemLocation.containsKey(position);
    }
    @Override
    public Object objectAt(Vector2d position) {
        if (elemLocation.containsKey(position))
        {
            if (elemLocation.get(position) instanceof  Animal) return (Animal) elemLocation.get(position);
            if (elemLocation.get(position) instanceof  Grass) return (Grass) elemLocation.get(position);
        }
        return null;
    }

}
