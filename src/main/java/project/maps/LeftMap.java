package project.maps;

import project.elements.Grass;
import project.visualization.MapVisualizer;
import project.orientation.Vector2d;

public class LeftMap extends  AbstractWorldMap
{
    public LeftMap(Vector2d mapLeftSize, Vector2d mapRightSize, int startEnergy, int moveEnergy, int plantEnergy, float jungleRatio, int rotateEnergy,int breedEnergy) {
        super(mapLeftSize, mapRightSize, startEnergy, moveEnergy, plantEnergy, jungleRatio, rotateEnergy, breedEnergy);
        setDrawer(new MapVisualizer(this));
    }

    @Override
    public Vector2d whereToMoveTo(Vector2d position)
    {
        if (position.follows(mapLeftSize) && position.precedes(mapRightSize))
            return position;
        else
        {
            int x, y;
            if (position.getX() > mapRightSize.getX()) x = mapLeftSize.getX();
            else if (position.getX() < mapLeftSize.getX()) x = mapRightSize.getX();
            else x = position.getX();

            if (position.getY() > mapRightSize.getY()) y = mapLeftSize.getY();
            else if (position.getY() < mapLeftSize.getY()) y = mapRightSize.getY();
            else y = position.getY();

            return new Vector2d(x, y);
        }
    }

    public void addGrass()
    {
        addElement(new Grass(getEmptyLocation(),this));
    }
}
