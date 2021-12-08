package project.maps;

import project.elements.Animal;
import project.elements.Grass;
import project.elements.IMapElement;
import project.orientation.Vector2d;

import java.util.ArrayList;

public class Field
{
    private final ArrayList<IMapElement> elementsOnField = new ArrayList<>();
    private boolean isGrass;
    private final IWorldMap map;
    private final Vector2d position;

    public Field(boolean isGrass, IWorldMap map, Vector2d position)
    {
        this.isGrass = isGrass;
        this.map = map;
        this.position = position;
    }

    public boolean getIsGrass() {
        return isGrass;
    }

    public void setGrass(boolean grass) {
        isGrass = grass;
    }

    public void addToField(IMapElement element)
    {
        this.elementsOnField.add(element);
    }

    public void RemoveFromField(IMapElement element)
    {
        this.elementsOnField.remove(element);
    }

    public  boolean isInField(IMapElement element)
    {
        return this.elementsOnField.contains(element);
    }

    public  boolean isFieldEmpty()
    {
        return this.elementsOnField.isEmpty();
    }

    public  IMapElement getRepresentative()
    {
        if (isGrass && this.elementsOnField.size() == 0) return getGrass();
        Animal animal = (Animal) this.elementsOnField.get(0);
        Animal animal2;
        for (int i = 1; i < this.elementsOnField.size(); i += 1)
        {
            animal2 = (Animal) this.elementsOnField.get(i);
            if (animal.getEnergy() < animal2.getEnergy())
            {
                animal = animal2;
            }
        }
        return animal;
    }

    public void removeGrass()
    {
            isGrass = false;
    }

    public Grass getGrass()
    {
        return new Grass(this.position, this.map);
    }

    public ArrayList<Animal> getEatingAnimals()
    {
        ArrayList<Animal> animals = new ArrayList<>();
        Animal animal = (Animal) elementsOnField.get(0);
        int maxEnergy = animal.getEnergy();
        animals.add(animal);
        for (int i = 1; i < elementsOnField.size(); i += 1)
        {
            animal = (Animal) elementsOnField.get(i);
            if (maxEnergy < animal.getEnergy())
            {
                maxEnergy = animal.getEnergy();
                animals.clear();
                animals.add(animal);
            }
            else if (animal.getEnergy() == maxEnergy) animals.add(animal);
        }
        return  animals;
    }

}
