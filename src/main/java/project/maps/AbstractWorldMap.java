package project.maps;

import project.*;
import project.elements.DeathNote;
import project.orientation.Vector2d;
import project.elements.Animal;
import project.elements.Grass;
import project.elements.IMapElement;
import project.visualization.MapVisualizer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver
{
    // POLA
    protected MapVisualizer drawer;
    protected final Vector2d mapLeftSize;
    protected final Vector2d mapRightSize;
    protected final int startEnergy;
    protected final int moveEnergy;
    protected final int plantEnergy;
    protected final float jungleRatio;
    protected final int rotateEnergy;
    protected final int breedEnergy;
    protected final LinkedHashMap<Vector2d, Field> fieldPosition = new LinkedHashMap<>();
    protected final LinkedList<Vector2d> consumePosition = new LinkedList<>();
    protected final LinkedList<DeathNote>  deathReportList = new LinkedList<>();
    protected final LinkedList<Animal>  breedingList = new LinkedList<>();


    // KONSTRUKTOR

    public AbstractWorldMap(Vector2d mapLeftSize, Vector2d mapRightSize, int startEnergy, int moveEnergy, int plantEnergy, float jungleRatio, int rotateEnergy, int breedEnergy)
    {
        this.mapLeftSize = mapLeftSize;
        this.mapRightSize = mapRightSize;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.rotateEnergy = rotateEnergy;
        this.plantEnergy = plantEnergy;
        this.jungleRatio = jungleRatio;
        this.breedEnergy = breedEnergy;
    }
    // OBSLUGA POL
    public void removeEmptyField(Vector2d position)
    {
        if (fieldPosition.get(position).isFieldEmpty() && !fieldPosition.get(position).getIsGrass()) fieldPosition.remove(position);
    }

    public void addElement(IMapElement element)
    {
        if (!fieldPosition.containsKey(element.getPosition())) fieldPosition.put(element.getPosition(), new Field(false, this, element.getPosition()));
        if (element instanceof Grass)   fieldPosition.get(element.getPosition()).setGrass(true);
        else
        {
            fieldPosition.get(element.getPosition()).addToField(element);
            element.addObserver(this);
        }
    }

    public Vector2d getEmptyLocation()
    {
        Vector2d position = new Vector2d(Generate.randInt(this.mapLeftSize.getX(), this.mapRightSize.getX()),
                Generate.randInt(this.mapLeftSize.getY(), this.mapRightSize.getY()));
        while (this.fieldPosition.containsKey(position))
        {
            position = new Vector2d(Generate.randInt(this.mapLeftSize.getX(), this.mapRightSize.getX()),
                    Generate.randInt(this.mapLeftSize.getY(), this.mapRightSize.getY()));
        }
        return position;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public int getMoveEnergy() {
        return moveEnergy;
    }

    public int getPlantEnergy() {
        return plantEnergy;
    }

    public float getJungleRatio() {
        return jungleRatio;
    }

    public int getRotateEnergy() {
        return rotateEnergy;
    }

    // NATYWNE OPERACJE
    protected void setDrawer(MapVisualizer drawer) {
        this.drawer = drawer;
    }

    public String toString()
    {
        return drawer.draw(this.mapLeftSize, this.mapRightSize);
    }


    @Override
    public boolean place(Animal animal) throws IllegalArgumentException
    {
        if (!(objectAt(animal.getPosition()) instanceof Animal))
        {
            addElement(animal);
            return true;
        }
        else
        {
            throw new IllegalArgumentException("Position at " + animal.getPosition()  +  " is occupied");
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        return true;
    } // TODO UPOSLEDZONE TO JEST TERAZ

    @Override
    public boolean isOccupied(Vector2d position) {
        return fieldPosition.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position)
    {
        if (fieldPosition.containsKey(position)) return fieldPosition.get(position).getRepresentative();
        return null;
    }

    // OBSERWATOR
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement element)
    {
        if (element instanceof Animal)
        {

            fieldPosition.get(oldPosition).RemoveFromField(element);
            removeEmptyField(oldPosition);
            if (!fieldPosition.containsKey(newPosition)) fieldPosition.put(newPosition, new Field(false, this, newPosition));
            if (fieldPosition.get(newPosition).getIsGrass()) this.consumePosition.add(newPosition); // Do konsumpcji trawy
            Animal animal = (Animal) element;
            if (animal.getEnergy() >= this.breedEnergy && !fieldPosition.get(newPosition).isFieldEmpty()) breedingList.add(animal);
            fieldPosition.get(newPosition).addToField(element);
        }
    }

    public void reportPossibleDeath(Vector2d position, Animal animal)
    {
        deathReportList.add(new DeathNote(animal, position));
    }

    // EVENTY
    public void grassEatingTime()
    {
        ArrayList<Animal> eatingAnimals;
        for (Vector2d position: consumePosition)
        {
            fieldPosition.get(position).removeGrass();
            eatingAnimals = fieldPosition.get(position).getEatingAnimals();
            int food = plantEnergy/eatingAnimals.size(); // Resztki rozdeptane
            for (Animal animal : eatingAnimals)
            {
                animal.setEnergy(animal.getEnergy() + food);
            }
        }
        consumePosition.clear();
    }

    public LinkedList<Animal> executeDeath()
    {
        LinkedList<Animal> deadAnimals = new LinkedList<>();
        for (DeathNote dead : deathReportList)
        {
            if (dead.animal.getEnergy() <= 0)
            {
                fieldPosition.get(dead.position).RemoveFromField(dead.animal);
                removeEmptyField(dead.position);
                deadAnimals.add(dead.animal);
            }
        }
        deathReportList.clear();
        return  deadAnimals;
    }

    public void breeding()
    {
        Vector2d position;
        for (Animal animal: breedingList)
        {
            position = animal.getPosition();

        }

    }

}
