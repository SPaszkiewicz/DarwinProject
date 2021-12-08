package project;
import project.elements.Animal;
import project.elements.IMapElement;
import project.orientation.Vector2d;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement abstractWorldElem);

    void reportPossibleDeath(Vector2d position, Animal animal);
}
