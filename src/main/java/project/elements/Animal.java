package project.elements;

import project.*;
import project.orientation.*;
import project.maps.IWorldMap;

import java.util.Arrays;


public class Animal extends AbstractWorldElem
{
    private MapDirection direction;
    private int energy;
    private final MoveDirection[] gen;

    public Animal(Vector2d position, IWorldMap map, MapDirection direction, int[] gen, int energy)
    {
        super(position, map);
        this.direction = direction;
        this.gen = DirectionParser.toDirection(gen);
        this.energy = energy;
    }

    @Override
    public String toString() {
        return Integer.toString(energy);
    }

    public MapDirection getDirection() {
        return direction;
    }

    public void rotate()
    {
        switch (this.chooseDirection())
        {
            case LIT_RIGHT:
            {
                this.direction = this.getDirection().next45();
                break;
            }
            case RIGHT:
            {
                this.direction = this.getDirection().next90();
                break;
            }
            case MOR_RIGHT:
            {
                this.direction = this.getDirection().next90();
                this.direction = this.getDirection().next45();
                break;
            }
            case BACKWARD:
                this.direction = this.getDirection().next90();
                this.direction = this.getDirection().next90();
                break;
            case LIT_LEFT:
                this.direction = this.getDirection().previous45();
                break;
            case LEFT:
                this.direction = this.getDirection().previous90();
                break;
            case MOR_LEFT:
                this.direction = this.getDirection().previous45();
                this.direction = this.getDirection().previous90();
                break;
            default: break;
        }
    }

    public void move()
    {
        Vector2d newPosition = map.WhereToMoveTo(this.position.add(direction.toUnitVector()));
        if (map.canMoveTo(newPosition))
        {
            if (map.objectAt(newPosition) instanceof Grass) // JEDZENIE TRAWY
            {
                this.energy += 1; //TODO ZMIANA O ILE KTO CHCE
                map.grassEaten(newPosition);
            }
            positionChanged(this.position, newPosition);
            this.position = newPosition;
        }
    }

    public MoveDirection chooseDirection()
    {
        return this.gen[Generate.RandInt(0, 31)];
    }
}
