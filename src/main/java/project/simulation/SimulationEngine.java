package project.simulation;

import project.Generate;
import project.IMapObserver;
import project.elements.Animal;
import project.maps.IWorldMap;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SimulationEngine implements IEngine, Runnable
{
    private final IWorldMap map;
    private final LinkedList<Animal> animalList = new LinkedList<>();
    private final LinkedList<IMapObserver> observers = new LinkedList<>();

    public SimulationEngine(IWorldMap map, int amountOfAnimals, int baseEnergy)
    {
        this.map = map;
        Animal Tymon; //TODO ZMIEN TO NAZWE


        for(int i = 0; i < amountOfAnimals; i +=1)
        {
            Tymon = Generate.createAnimal(this.map, baseEnergy);
            if (Tymon.getEnergy() > 0)
            {
                map.place(Tymon);
                animalList.add(Tymon);
            }
        }
    }

    public void nextday() throws FileNotFoundException {
        int prevLivingExpectancy = 0;
        int prevDeathAmount = 0;
        LinkedList<Animal> deadAnimals;
        ArrayList<Animal> newAnimals;
        deadAnimals = map.executeDeath();
        for (Animal animal: deadAnimals)
        {
            prevDeathAmount += 1;
            prevLivingExpectancy += animal.getDayLiving();
            animalList.remove(animal);
        }

        mapChanged(map, makeStatistics(prevLivingExpectancy, prevDeathAmount));

        for (Animal animal: animalList)
        {
            animal.move();
        }
        map.grassEatingTime();

        newAnimals = map.breeding();
        animalList.addAll(newAnimals);
        map.addGrassToJungle();
        map.addGrassToSteppe();
    }

    @Override
    public IWorldMap getMap() {
        return map;
    }

    private Statistics makeStatistics(int prevLivingExpectancy, int prevDeathAmount)
    {
        int amountOfAnimals = 0;
        int amountOfGrass = map.getAmountOfGrass();
        int sumOfEnergy = 0;
        int sumOfKids = 0;
        int averageEnergyPerAnimal;
        int averageKidsPerAnimal;
        int lifeExpectancy;
        for (Animal animal : animalList)
        {
            amountOfAnimals += 1;
            sumOfEnergy += animal.getEnergy();
            sumOfKids += animal.getBreedAmount();
        }
        if (amountOfAnimals > 0)
        {
            averageEnergyPerAnimal = (sumOfEnergy/amountOfAnimals);
            averageKidsPerAnimal = (sumOfKids/amountOfAnimals);
        }
        else
        {
            averageEnergyPerAnimal = 0;
            averageKidsPerAnimal = 0;
        }
        if (prevDeathAmount > 0)
            lifeExpectancy = (prevLivingExpectancy/prevDeathAmount);
        else
            lifeExpectancy = 0;
        return  new Statistics(averageEnergyPerAnimal, amountOfGrass, amountOfAnimals, averageKidsPerAnimal, lifeExpectancy);
    }

    @Override
    public void run()
    {
        try {
            int i = 0;
            System.out.println(this.map);

            while (i < 50)
            {
                nextday();
                Thread.sleep(1000);
                i += 1;
            }
        } catch (InterruptedException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //OBSERWATOR
    public void addObserver(IMapObserver observer)
    {
        observers.add(observer);
    }


    public void mapChanged(IWorldMap map, Statistics statistic) throws FileNotFoundException {
        for (IMapObserver observer: observers)
        {
            observer.mapChanged(map, statistic);
        }
    }
}
