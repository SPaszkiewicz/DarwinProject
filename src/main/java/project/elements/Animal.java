package project.elements;

import project.*;
import project.orientation.*;
import project.maps.IWorldMap;


public class Animal extends AbstractWorldElem
{
    private MapDirection direction;
    private int energy;
    private final MoveDirection[] gen;
    private int dayLiving = 0;
    private int breedAmount = 0;
    public Animal(Vector2d position, IWorldMap map, MapDirection direction, MoveDirection[] gen, int energy)
    {
        super(position, map);
        this.direction = direction;
        this.gen = gen;
        this.energy = energy;
    }

    public int getDayLiving() {
        return dayLiving;
    }

    public int getBreedAmount() {
        return breedAmount;
    }

    public void setBreedAmount(int breedAmount) {
        this.breedAmount = breedAmount;
    }

    public MoveDirection[] getGen() {
        return gen;
    }

    public int getEnergy() {
        return energy;
    }


    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return Integer.toString(energy);
    }

    public MapDirection getDirection() {
        return direction;
    }

    public void positionChanged(Vector2d oldVector, Vector2d newVector)
    {
        for (IPositionChangeObserver observer: observers)
        {
            observer.positionChanged(oldVector, newVector, this);
        }
    }

    public MoveDirection rotate()
    {
        MoveDirection rotation = this.chooseDirection();
        switch (rotation)
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
        return rotation;
    }
    public void reportDeadPossibility(Vector2d position)
    {
        for (IPositionChangeObserver observer: observers)
        {
            observer.reportPossibleDeath(position, this);
        }
    }
    public void move()
    {
        dayLiving += 1;
        Vector2d newPosition = map.whereToMoveTo(this.position.add(direction.toUnitVector()));
        MoveDirection rotation = this.rotate();
        if (map.canMoveTo(newPosition) && (rotation == MoveDirection.FORWARD))
        {
            this.energy = this.energy - this.map.getMoveEnergy();
            positionChanged(this.position, newPosition);
            this.position = newPosition;
        }
        else if (rotation != MoveDirection.FORWARD)this.energy = this.energy - this.map.getRotateEnergy();

        if (this.energy <= 0) this.reportDeadPossibility(this.position);
    }

    public MoveDirection chooseDirection()
    {
        return this.gen[Generate.randInt(0, 31)];
    }
}
