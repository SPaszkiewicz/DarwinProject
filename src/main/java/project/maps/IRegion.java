package project.maps;

import project.orientation.Vector2d;

public interface IRegion
{
    boolean inRegion(Vector2d position);

    Vector2d getRandomPosition();

    void setMap(IWorldMap map);
}
