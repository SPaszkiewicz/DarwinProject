package project;

public class Animal extends  AbstractWorldElem
{
    private MapDirection direction;
    private int energy;
    private String[] gen;

    public Animal(Vector2d position, IWorldMap map, MapDirection direction)
    {
        super(position, map);
        this.direction = direction;
    }

    public MapDirection getDirection() {
        return direction;
    }

    public void move(MoveDirection direction)
    {

    }
}
