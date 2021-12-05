package project;

abstract class AbstractWorldElem implements IMapElement
{
    protected final Vector2d position;
    protected IWorldMap map;

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

    @Override
    public Vector2d getLocation()
    {
        return null;
    }

}