package project.simulation;

import project.Generate;
import project.elements.Animal;
import project.frontend.IMapObserver;
import project.maps.IWorldMap;
import project.orientation.Vector2d;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class SimulationEngine implements IEngine, Runnable {
    private final IWorldMap map;
    private final LinkedList<Animal> animalList = new LinkedList<>();
    private final LinkedList<IMapObserver> observers = new LinkedList<>();
    private boolean isRunned = true;
    private boolean exit = true;
    private int prevLivingExpectancy = 0;
    private int prevDeathAmount = 0;
    private int day = 0;
    private final boolean isMagic;
    private int tries = 3;
    private final int baseEnergy;

    public SimulationEngine(IWorldMap map, int amountOfAnimals, int baseEnergy, boolean isMagic)
    {
        this.isMagic = isMagic;
        this.map = map;
        this.baseEnergy = baseEnergy;
        Animal animal;


        for (int i = 0; i < amountOfAnimals; i += 1) {
            animal = Generate.createAnimal(this.map, baseEnergy);
            if (animal.getEnergy() > 0) {
                map.place(animal);
                animalList.add(animal);
            }
        }
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public int getTries() {
        return tries;
    }

    public int getDay() {
        return day;
    }

    public void nextday() throws FileNotFoundException {
        LinkedList<Animal> deadAnimals;
        ArrayList<Animal> newAnimals;
        deadAnimals = map.executeDeath();
        for (Animal animal : deadAnimals) {
            this.prevDeathAmount += 1;
            this.prevLivingExpectancy += animal.getDayLiving();
            animalList.remove(animal);
        }


        for (Animal animal : animalList) {
            animal.move();
        }

        map.grassEatingTime();

        newAnimals = map.breeding();

        animalList.addAll(newAnimals);

        map.addGrassToJungle();
        map.addGrassToSteppe();

        mapChanged(map, makeStatistics(newAnimals));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.day += 1;
    }

    public void clearMarkFromAnimals()
    {
        for (Animal animal: animalList)
        {
            animal.setDescendant(false);
        }
    }

    @Override
    public IWorldMap getMap()
    {
        return map;
    }

    public int[] dominants()
    {
        ArrayList<int[]>  genes = new ArrayList<>();
        for (Animal animal: animalList) genes.add(animal.getDominants());
        genes.sort(Comparator.comparingInt(a -> a[7]));
        genes.sort(Comparator.comparingInt(a -> a[5]));
        genes.sort(Comparator.comparingInt(a -> a[4]));
        genes.sort(Comparator.comparingInt(a -> a[3]));
        genes.sort(Comparator.comparingInt(a -> a[2]));
        genes.sort(Comparator.comparingInt(a -> a[1]));
        genes.sort(Comparator.comparingInt(a -> a[0]));
        int maxim = 1;
        int current = 1;
        int[] solution = new int[0];

        for (int i = 1; i < genes.size(); i+= 1)
        {
            if (isEqual(genes.get(i), genes.get(i - 1))) current += 1;
            else current = 1;
            if (current > maxim)
            {
                maxim = current;
                solution = genes.get(i);
            }
        }
        if (maxim <= 1) return null;
        else return solution;
    }

    private boolean isEqual(int[] a, int[] b)
    {
        for (int i = 0; i < 8; i += 1)
        {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    private Statistics makeStatistics(ArrayList<Animal> newAnimals) {
        int amountOfAnimals = 0;
        int amountOfGrass = map.getAmountOfGrass();
        int sumOfEnergy = 0;
        int sumOfKids = 0;
        int averageEnergyPerAnimal;
        int averageKidsPerAnimal;
        int lifeExpectancy;
        int descendants = 0;
        for (Animal animal : this.animalList)
        {
            amountOfAnimals += 1;
            sumOfEnergy += animal.getEnergy();
            sumOfKids += animal.getBreedAmount();
        }
        for (Animal animal : newAnimals) if (animal.isDescendant()) descendants += 1;
        if (amountOfAnimals > 0) {
            averageEnergyPerAnimal = (sumOfEnergy / amountOfAnimals);
            averageKidsPerAnimal = (sumOfKids / amountOfAnimals);
        } else {
            averageEnergyPerAnimal = 0;
            averageKidsPerAnimal = 0;
        }
        if (this.prevDeathAmount > 0)
            lifeExpectancy = (this.prevLivingExpectancy / this.prevDeathAmount);
        else
            lifeExpectancy = 0;
        return new Statistics(averageEnergyPerAnimal, amountOfGrass, amountOfAnimals, averageKidsPerAnimal, lifeExpectancy, descendants, gensToString(dominants()));
    }

    public String gensToString(int[] elem)
    {
        if (elem == null) return " ";
        StringBuilder gens = new StringBuilder();
        for (int i =0; i < 8; i += 1)
        {
            gens.append(String.valueOf(i).repeat(Math.max(0, elem[i])));
        }
        return gens.toString();
    }


    @Override
    public void run() {
        try {
            while (exit) {
                sleep();
                nextday();
                if (isMagic && this.tries > 0 && this.animalList.size() <= 5) addFromMagic();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sleep() {
        while (!isRunned && exit) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addFromMagic()
    {
        Animal child;
        LinkedList<Animal> newAnimals = new LinkedList<>();
        if (this.animalList.size() != 0) {
            for (int i = 1; i < 6; i += 1) {
                child = Generate.magicAnimal(this.map, this.baseEnergy, this.map.getEmptyLocation(), this.animalList.get(i % this.animalList.size()).getGen());
                if (!child.getPosition().equals(new Vector2d(-1, -1)))
                {
                    newAnimals.add(child);
                    map.place(child);
                }
            }
            this.tries -= 1;
            this.animalList.addAll(newAnimals);
        }
    }


    public void stop() {
        this.isRunned = false;
    }

    public void start() {
        this.isRunned = true;
    }

    //OBSERWATOR
    public void addObserver(IMapObserver observer) {
        observers.add(observer);
    }


    public void mapChanged(IWorldMap map, Statistics statistic) throws FileNotFoundException {
        for (IMapObserver observer : observers) {
            observer.mapChanged(map, statistic);
        }
    }
}
