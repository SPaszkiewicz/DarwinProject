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
    // POLA PODSTAWOWE
    protected MapVisualizer drawer;
    protected final Vector2d mapLeftSize;
    protected final Vector2d mapRightSize;
    protected final LinkedHashMap<Vector2d, Field> fieldPosition = new LinkedHashMap<>();
    protected final LinkedList<Vector2d> consumePosition = new LinkedList<>();
    protected final LinkedList<DeathNote>  deathReportList = new LinkedList<>();
    protected final IRegion jungle;
    protected final IRegion steppe;

    //POLA WEJSCIOWE
    protected final int startEnergy;
    protected final int moveEnergy;
    protected final int plantEnergy;
    protected final float jungleRatio;
    protected final int rotateEnergy;
    protected final int breedEnergy;

    //POLA NA STATYSTYKI
    protected int amountOfGrass = 0;

    // KONSTRUKTOR

    public AbstractWorldMap(Vector2d mapLeftSize, Vector2d mapRightSize, int startEnergy, int moveEnergy, int plantEnergy, float jungleRatio, int rotateEnergy)
    {

        this.mapLeftSize = mapLeftSize;
        this.mapRightSize = mapRightSize;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.rotateEnergy = rotateEnergy;
        this.plantEnergy = plantEnergy;
        this.jungleRatio = jungleRatio;
        this.breedEnergy = startEnergy/2;

        int jungleSizeX = (int) ((mapRightSize.getX() - mapLeftSize.getX()) * jungleRatio);
        int jungleSizeY = (int) ((mapRightSize.getY() - mapLeftSize.getY()) * jungleRatio);
        Vector2d jungleStart = new Vector2d((mapRightSize.getX() - mapLeftSize.getX()-jungleSizeX)/2, (mapRightSize.getY() - mapLeftSize.getY() - jungleSizeY)/2);
        Vector2d jungleEnd = new Vector2d(jungleStart.getX() + jungleSizeX, jungleStart.getY() + jungleSizeY);
        this.jungle = new Jungle(jungleStart, jungleEnd, this);
        this.steppe = new Steppe(jungleStart, jungleEnd, this, mapLeftSize, mapRightSize);

    }
    // OBSLUGA POL
    public void removeEmptyField(Vector2d position)
    {
        if (fieldPosition.get(position).isFieldEmpty() && !fieldPosition.get(position).getIsGrass()) fieldPosition.remove(position);
    }

    protected void setJungleMap(IWorldMap map)
    {
        jungle.setMap(map);
    }

    protected void setSteppeMap(IWorldMap map)
    {
        steppe.setMap(map);
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

    public boolean isInJungle(Vector2d position)
    {
     return this.jungle.inRegion(position);
    }

    public Vector2d getEmptyLocation()
    {
        ArrayList<Vector2d> consideredVectors = new ArrayList<>();
        for (int i = mapLeftSize.getX(); i < mapRightSize.getX()+1; i += 1)
        {
            for (int j = mapLeftSize.getY(); j < mapRightSize.getY()+1; j += 1)
            {
                if (!this.isOccupied(new Vector2d(i, j))) consideredVectors.add(new Vector2d(i, j));
            }
        }
        if (consideredVectors.size() > 0) return consideredVectors.get(Generate.randInt(0, consideredVectors.size()-1));
        else return new Vector2d(-1, -1);
    }

    public Vector2d getMapRightSize() {
        return mapRightSize;
    }

    public int getAmountOfGrass() {return amountOfGrass;}


    public int getMoveEnergy() {
        return moveEnergy;
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
    }

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
            if (fieldPosition.get(newPosition).getIsGrass() && !consumePosition.contains(newPosition)) this.consumePosition.add(newPosition); // Do konsumpcji trawy
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
            amountOfGrass -= 1;
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

}
