package project;
import project.orientation.Vector2d;

public interface IPositionChangeObserver
{
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
