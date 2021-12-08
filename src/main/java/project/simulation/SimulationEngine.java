package project.simulation;

import project.Generate;
import project.elements.Animal;
import project.maps.IWorldMap;
import java.util.LinkedList;

public class SimulationEngine implements IEngine
{
    private final IWorldMap map;
    private final LinkedList<Animal> animalList = new LinkedList<>();

    public SimulationEngine(IWorldMap map, int amountOfAnimals, int baseEnergy, int amountOfGrass)
    {
        this.map = map;
        Animal Tymon; //TODO ZMIEN TO NAZWE

        for(int i = 0; i < amountOfGrass; i +=1) map.addGrass();

        for(int i = 0; i < amountOfAnimals; i +=1)
        {
            Tymon = Generate.createAnimal(this.map, baseEnergy);
            map.place(Tymon);
            animalList.add(Tymon);
        }
    }


    @Override
    public void run()
    {
        int i = 0;
        System.out.println(this.map);
        LinkedList<Animal> deadAnimals;
        while (i < 50) {
            deadAnimals = map.executeDeath();
            System.out.println(this.map);
            for (Animal animal: deadAnimals)
            {
                animalList.remove(animal);
            }
            for (Animal animal: animalList)
            {
                animal.move();
            }
            map.grassEatingTime();
            i += 1;
        }
    }
}
