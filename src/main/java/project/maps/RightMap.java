package project.maps;

import project.elements.Grass;
import project.orientation.Vector2d;
import project.visualization.MapVisualizer;

public class RightMap  extends  AbstractWorldMap
{

    public RightMap(Vector2d mapLeftSize, Vector2d mapRightSize)
    {
        super(mapLeftSize, mapRightSize);
        setDrawer(new MapVisualizer(this));
    }

    @Override
    public Vector2d WhereToMoveTo(Vector2d position) {
        return position;
    }

    @Override
    public void addGrass() //TODO PEWNIE DA SIE LEPIEJ
    {
        addElement(new Grass(getEmptyLocation(),this));
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        return (position.follows(super.mapLeftSize) && (position.precedes(super.mapRightSize)) && super.canMoveTo(position));
    }
}
