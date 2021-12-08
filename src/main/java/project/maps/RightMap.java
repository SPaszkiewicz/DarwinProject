package project.maps;

import project.elements.Grass;
import project.orientation.Vector2d;
import project.visualization.MapVisualizer;

public class RightMap  extends  AbstractWorldMap
{

    public RightMap(Vector2d mapLeftSize, Vector2d mapRightSize, int startEnergy, int moveEnergy, int plantEnergy, float jungleRatio, int rotateEnergy, int breedEnergy) {
        super(mapLeftSize, mapRightSize, startEnergy, moveEnergy, plantEnergy, jungleRatio, rotateEnergy, breedEnergy);
        setDrawer(new MapVisualizer(this));
    }

    @Override
    public Vector2d whereToMoveTo(Vector2d position) {
        return position;
    }

    @Override
    public void addGrass()
    {
        addElement(new Grass(getEmptyLocation(),this));
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        return (position.follows(super.mapLeftSize) && (position.precedes(super.mapRightSize)) && super.canMoveTo(position));
    }
}
